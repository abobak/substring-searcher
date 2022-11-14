# Substring searcher application

## Building and running

1. Running as standalone java application
- build project using maven command "mvn clean install"
- browse to "target" directory and run jar file "java -jar app-0.0.1-SNAPSHOT.jar"
2. Running as docker container
- run unix_build.sh or windows_build.bat to create a docker image under your operating system
- run "docker run -d -p 8080:8080 substring-app" to start the container on port 8080 or "docker-compose up ." to achieve the same result

## API
Assuming the application is run on localhost domain and port 8080 the following paths are supported

GET methods:

localhost:8080/tasks - lists all tasks

localhost:8080/tasks/{task-id} - shows status of task with given id, e.g. localhost:8080/tasks/bc997289-68aa-47fe-99e9-892fa13d2e00

POST methods:

localhost:8080/tasks - creates a new task, its payload must be represented as a request body in json format as below

{ "input": "AAAB", "pattern": "AAB" }

