Guidance:
1. install docker on your mac
2. execute "docker build -t <name of image> ."
3. execute "docker run -it --rm -p 8009:8009 -v <path to repo folder on your local mac>:/usr/src/project <name of image> bash"
4. using vscode install extension "dev containers" and utilising it attach and open running container
5. open /usr/src/project in vscode and run "npm ci" for installing project's dependencies
6. open "/usr/src/project/client" directory and run "npm run build" to build client's files
7. go back to main directory and execute "npm run start" to start the server
8. enjoy :)


