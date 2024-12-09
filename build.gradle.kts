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

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}
tasks.test {
    useJUnitPlatform()
}

tasks.withType<Test> {
    useJUnitPlatform()

}
tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint")
    options.compilerArgs.add("-parameters")
    options.encoding = "US-ASCII"
    options.isWarnings = true
}

