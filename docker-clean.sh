#!/usr/bin/env bash

#generate backend jar
./gradlew clean

rm ./docker/api/api.jar

docker-compose -f docker/docker-compose.yml down