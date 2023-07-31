

plugins {
    idea
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    id("org.liquibase.gradle") version "2.0.4"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

group = "com.fomichev"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

buildscript {
    extra.apply {
        set("minSdkVersion", 26)
        set("targetSdkVersion", 27)
    }
    repositories {
        google()
        gradlePluginPortal()
    }
}

subprojects {
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("mysql:mysql-connector-java:5.1.49")
    implementation("org.springframework.kafka:spring-kafka:2.8.6")
    implementation("org.liquibase:liquibase-core:4.20.0")
    implementation("org.yaml:snakeyaml:1.32")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
//    implementation("com.squareup.okhttp3:okhttp:3.14.9")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
