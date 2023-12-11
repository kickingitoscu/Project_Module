import express from 'express';
import dotenv from 'dotenv';
import multer from 'multer';
import {spawn} from 'node:child_process';
import session from 'express-session';


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

router.use('/', express.static('./static'));

router['post']('/api/uploadFiles', upload.fields([
    { name: 'images'},
    { name: 'header', maxCount: 1 },
    { name: 'subheader', maxCount: 1 }
  ]), async(request, response) => {
    console.log('called');
    const basePath = `./uploads/${request.sessionID}`;
    for(const image of request.files.images) {
        const imagePath = `${basePath}/raw/${image.originalname}`;
        const outputDirectory = `${basePath}/versions-${image.originalname.substring(0, image.originalname.length - 4)}`;
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
            
            const process = spawn('java', ["./generator/src/main/java/de/fhws/fiw/instagram/photoImage/PhotoImage.java", `${basePath}/images.properties`]);
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
            
    }
    // response.json('some reponse');



    
    // await new Promise((resolve, reject) => {
    //     const process = spawn('python', ['./ml/main.py']);
    //     process.stdout.on('data', (data) => {
    //         console.log(`stdout: ${data}`);
    //     });
          
    //     process.stderr.on('data', (data) => {
    //     console.error(`stderr: ${data}`);
    //     reject();
    //     });
        
    //     process.on('close', (code) => {
    //     console.log(`child process exited with code ${code}`);
    //     resolve();
    //     }); 
    // });

});




const port = process.env.PORT;
const path = process.env.HOST;

application.use(router).listen(port, async() => {
    console.log(`HTTP server is up, ${path}:${port}`)
})
