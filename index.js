import express from 'express';
import dotenv from 'dotenv';
import {spawn} from 'node:child_process';
dotenv.config();
const application = express();

const router = express.Router();

router.use('/', express.static('./static'));

router['post']('/api/uploadFiles', async(request, response) => {
    console.log('called');
    response.json('some reponse');
    await new Promise((resolve, reject) => {
        const process = spawn('python', ['./ml/main.py']);
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

});


const port = process.env.PORT;
const path = process.env.HOST;

application.use(router).listen(port, async() => {
    console.log(`HTTP server is up, ${path}:${port}`)
})
