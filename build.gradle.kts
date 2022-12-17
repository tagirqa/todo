import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.qameta.allure") version "2.11.2"
    kotlin("jvm") version "1.7.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.20")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy( "allureReport")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}