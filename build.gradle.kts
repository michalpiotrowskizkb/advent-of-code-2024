defaultTasks("clean", "build", "installDist")

plugins {
    kotlin("jvm") version "2.1.0"
}

repositories {
    maven(url = "https://nexus.prod.zkb.ch/content/groups/public")
}

configurations.all {
    resolutionStrategy {
        failOnVersionConflict()
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint")
    options.compilerArgs.add("-parameters")
    options.encoding = "US-ASCII"
}
