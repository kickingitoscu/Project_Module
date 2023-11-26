import express from 'express';
import dotenv from 'dotenv';
dotenv.config();
const application = express();

const port = process.env.PORT;
const path = process.env.HOST;

application.listen(port, async() => {
    console.log(`HTTP server is up, ${path}:${port}`)
})
