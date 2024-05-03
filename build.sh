#!/bin/bash

# clean & package java in jar
mvn clean install

# build docker containers for arm & amd
docker build -t ebensemere/coursenotes-backend:mac --push .
docker buildx build --platform linux/amd64 -t ebensemere/coursenotes-backend:linux --push .

# start containers
docker compose up

echo "build complete"
