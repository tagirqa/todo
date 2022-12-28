import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm") version "1.7.20"
    id("io.gitlab.arturbosch.detekt").version("1.22.0")
    id("io.qameta.allure") version "2.11.2"
    application
}

detekt {
    ignoreFailures = true
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.20")

    testImplementation ("org.spekframework.spek2:spek-dsl-jvm:2.0.17")
    testRuntimeOnly ("org.spekframework.spek2:spek-runner-junit5:2.0.17")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Detekt>().configureEach() {
    reports {
        html.required.set(true)
    }
}

application {
    mainClass.set("MainKt")
}