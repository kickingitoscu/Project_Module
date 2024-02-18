import express from 'express';
import dotenv from 'dotenv';
import multer from 'multer';
import {spawn} from 'node:child_process';
import session from 'express-session';
import path from 'path';
import * as fs from 'fs';

function makeid(length) {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;
    let counter = 0;
    while (counter < length) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
      counter += 1;
    }
    return result;
}

dotenv.config();
const application = express();
const storage = multer.diskStorage({  destination: function (req, file, cb) {
    const provisionalFolder = `uploads/${req.sessionID}/`;
    if (!fs.existsSync(provisionalFolder)) {
        fs.mkdirSync(provisionalFolder);
        fs.mkdirSync(`${provisionalFolder}/raw/`)
    }
    cb(null, `${provisionalFolder}/raw/`); 
  },
  filename: function (req, file, cb) {
    file.originalname = makeid(5) + file.originalname.substring(file.originalname.lastIndexOf('.'));
    cb(null, file.originalname); 
  } })
// const storage = multer.memoryStorage(); // You can define your storage settings
const upload = multer({storage: storage});

const router = express.Router();

application.use(session({
    secret: 'your_secret_key',
    resave: false,
    saveUninitialized: true
}));

const indexPath = path.join(path.resolve(), "./static", "index.html");
router.use(express.static('./static'));

router['get']("/posts/:param", (req, res) => {
    res.sendFile(indexPath);
});

router['get']("/", (req, res) => {
    res.sendFile(indexPath);
});

router['post']('/api/uploadFiles', upload.fields([
    { name: 'images'},
    { name: 'header', maxCount: 1 },
    { name: 'subheader', maxCount: 1 }
  ]), async(request, response) => {
    if (!request.body.header || !request.body.subheader) {
        response.status(403).json({msg: "Either header or subheader is not specified!"});
        return;
    }
    if (!('images' in request.files)) {
        response.status(403).json({msg: "No images provided!"});
        return;
    }

    let buffer = ""
    const basePath = `./uploads/${request.sessionID}`;
    await new Promise((resolve, reject) => {
        const process = spawn('python', ['./ml/principles.py', `${basePath}/raw`]);
            process.stdout.on('data', (data) => {
                buffer += data.toString();
            });
              
            process.stderr.on('data', (data) => {
                console.error(`stderr: ${data}`);
                reject();
            });
            
            process.on('close', (code) => {
                console.log(`child process exited with code ${code}`);
                resolve();
            }); 
    });
    const imagesToRemove = eval(buffer);

    for (const file of imagesToRemove) {
        fs.unlink(file, (err => {
            if (err) console.log(err);
        }));
    }

    if (fs.readdirSync(`${basePath}/raw`).length === 0) {
        response.status(403).json({msg: "All the provided images are not valid: either faces in the corners or color palette contains non-THWS-colors!"});
        return;
    }

    let finalPredictions = {};
    for(const image of request.files.images) {
        const imagePath = `${basePath}/raw/${image.originalname}`;
        const outputDirectory = `${basePath}/versions-${image.originalname.substring(0, image.originalname.length - 4)}/`;
        if (!fs.existsSync(outputDirectory)) fs.mkdirSync(outputDirectory);
        const bashParameterList = ["create-properties.sh", `./uploads/${request.sessionID}`, imagePath, outputDirectory];
        if (!fs.existsSync(`${basePath}/images.properties`)) bashParameterList.push(...[request.body.header, request.body.subheader])
        
        await new Promise((resolve, reject) => {
            
            const process = spawn('bash', bashParameterList);
            process.stdout.on('data', (data) => {
                console.log(`stdout: ${data}`);
            });
              
            process.stderr.on('data', (data) => {
            console.error(`stderr: ${data}`);
            reject();
            });
            
            process.on('close', (code) => {
            console.log(`child process exited with code ${code}`);
            resolve();
            }); 
        });
        await new Promise((resolve, reject) => {
            const classPath = "/usr/src/project/generator/target/classes"
            const process = spawn('java', ["-cp", classPath, "de.fhws.fiw.instagram.photoImage.PhotoImage", `${basePath}/images.properties`]);
            process.stdout.on('data', (data) => {
                console.log(`stdout: ${data}`);
            });
              
            process.stderr.on('data', (data) => {
                console.error(`stderr: ${data}`);
                reject();
            });
            
            process.on('close', (code) => {
                console.log(`child process exited with code ${code}`);
                resolve();
            }); 
        });
        
        buffer = ""
        await new Promise((resolve, reject) => {
            const process = spawn('python', ['./ml/main.py', outputDirectory], {
                maxBuffer: 1000 * 1024 * 1024
              });
            process.stdout.on('data', (data) => {
                buffer += data.toString();
            });
              
            process.stderr.on('data', (data) => {
                console.error(`stderr: ${data}`);
                reject();
            });
            
            process.on('close', (code) => {
            console.log(`child process exited with code ${code}`);
            resolve();
            }); 
        });
        finalPredictions = { ...finalPredictions, ...JSON.parse(buffer) };       
    }
    const imageEntries = Object.entries(finalPredictions);

    // Sort the array based on values in descending order
    imageEntries.sort((a, b) => b[1] - a[1]);

    // Return the first 5 key-value pairs
    const topFive = imageEntries.slice(0, 5);
    const folderId = makeid(16);
    const finalFolder = 'final/' + folderId;
    fs.mkdirSync(finalFolder);
    for (const [imagePath, rank] of topFive) {
        fs.rename(basePath + '/' + imagePath, finalFolder + `/${rank.toString()}_${makeid(5)}${imagePath.substring(imagePath.length - 4)}`, (err) => {
            if (err) {
              console.error('Error moving the file:', err);
            } else {
              console.log('File moved successfully.');
            }
          });
    }
    
    fs.rmSync(basePath, { recursive: true, force: true });
    response.json({
        ok: true,
        id: folderId
    });

});

router['get']('/getPosts', (req, res) => {
    const id = req.query.id;
    const files = [];
    const base = './final/' + id + '/';
    for (const filename of fs.readdirSync(base)) {
        const fileData = fs.readFileSync(base + filename);
        files.push({
            rank: parseFloat(filename.split('_')[0]),
            data: fileData.toString('base64')
        });
    }
    fs.rmSync(base, { recursive: true, force: true });
    res.json({images: files});
});

router['get']("*", (req, res) => {
    res.redirect("/");
});


const port = process.env.PORT;
const host = process.env.HOST;

application.use(router).listen(port, async() => {
    console.log(`HTTP server is up, ${host}:${port}`)
})
