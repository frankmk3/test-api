import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("application")
    id("com.avast.gradle.docker-compose") version "0.17.7"
}

val openapi = "1.8.0"

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")

    //  documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    //  Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.assertj:assertj-core:3.26.0")
    testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:4.1.3")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application { mainClass.set("com.frankmk3.testapi.TestApiApplicationKt") }

tasks.named<BootJar>("bootJar") {
    enabled = true
    archiveFileName.set("api.jar")
    doLast {
        copy {
            from("build/libs")
            into("../build/libs")
        }
    }
}

val intTest by
    sourceSets.creating {
        kotlin.srcDir("src/intTest/kotlin")
        resources.srcDir("src/intTest/resources")
        compileClasspath += sourceSets.main.get().output + configurations["testRuntimeClasspath"]
        runtimeClasspath += output + compileClasspath
    }

dockerCompose {
    stopContainers.set(false)
    forceRecreate.set(false)
    ignorePullFailure.set(false)
    waitForTcpPorts.set(false)

    setProjectName("test-api")
    useComposeFiles.value(listOf("../docker/docker-compose-dev.yml"))
    isRequiredBy(project.tasks.named("run"))
    with(nested("it")) {
        setProjectName("${project.name}-it")
        stopContainers.set(false)
        forceRecreate.set(false)
        ignorePullFailure.set(false)
        waitForTcpPorts.set(true)
        useComposeFiles.value(listOf("../docker/docker-compose-it.yml"))

        isRequiredBy(project.tasks.named("check"))
    }
    //    spring.data.redis.port
}

tasks.register<Test>("intTest") {
    dependsOn("itComposeUp")
    testClassesDirs = intTest.output.classesDirs
    classpath = intTest.runtimeClasspath
    shouldRunAfter("test")
    doFirst {
        val dbInfo = dockerCompose.nested("it").servicesInfos["redis"]!!
        environment["spring.data.redis.port"] = dbInfo.port
    }
}

tasks.named("check") { dependsOn("intTest") }
