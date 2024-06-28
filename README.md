# Getting Started

This service is a simple example of a REST API that uses Redis as a storage.
The service has a post endpoint to store a simple entity with a string value.
The service has a get endpoint to retrieve the entity by the id.

The architecture of the service is based on a simple **hexagonal architecture**, where the service is divided into layers:
- **Domain**: Layer responsible for the entities of the service.
- **Application**: Layer responsible for the business rules of the service.
- **Infrastructure**: Layer responsible for the external dependencies of the service.

### Pre requisites
[Docker](https://docs.docker.com)
[Docker Compose](https://docs.docker.com/compose/install/)


### Installation

Run the command to generate all the artifacts and start the docker-compose.

```shell
chmod +x docker-build.sh 
./docker-build.sh
```


### Service SWAGGER
To access the service swagger, open the browser and access the url:
http://localhost:8484/swagger-ui.html


### Remove the service

Run the command to remove the service.

```shell
chmod +x docker-clean.sh 
./docker-clean.sh
```

## Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.1/gradle-plugin/reference/html/#build-image)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/docs/3.3.1/reference/htmlsingle/index.html#data.nosql.redis)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.3.1/reference/htmlsingle/index.html#features.docker-compose)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.1/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
