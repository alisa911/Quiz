FROM openjdk:11-jdk-slim-buster as builder

ARG JAR_FILE=target/quiz.jar
WORKDIR /quiz/src