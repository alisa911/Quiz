### Quiz game web app

Demo: https://quiz-spring-boot.herokuapp.com/

#### Installation and Launch
##### Pre-Requisites:
Please make sure you have following tools installed before continuing:
- Java 11
- PostgresSQL 10+

##### Set Up Database

* Create an empty PostgreSQK database;
* Create a new file named `.env` in project root directory.
* Put following content into `.env` replacing example values with your database credentials:

```
QUIZ_DB_USERNAME=****
QUIZ_DB_URL=jdbc:postgresql://localhost:5432/postgres
QUIZ_DB_DOCKER_URL=jdbc:postgresql://docker.for.mac.localhost:5432/postgres
```
Run in terminal:
```
$ . ./env.sh 
```
Create database tables running following SQL file in your database: `<project_roo>/src/main/resources/db/init-tables.sql.`

Run following commands in terminal:
```
$ java -jar path to jar .../quiz/target/quiz-1.0-SNAPSHOT.jar
```
Run with Docker:
```
$ ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=plotva/quiz
$ docker-compose up
```
#### Done
Open http://localhost:8080 in your browser.