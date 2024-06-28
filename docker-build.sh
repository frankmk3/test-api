#!/usr/bin/env bash

#generate backend jar
./gradlew :infrastructure:build

cp ./infrastructure/build/libs/api.jar ./docker/api/api.jar

chmod +x ./docker/api/api.jar

docker-compose -f docker/docker-compose.yml up -d