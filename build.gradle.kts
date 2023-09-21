plugins {
    kotlin("jvm") version "1.9.10"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "raystark"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-logging-jvm:2.3.4")
    implementation("io.ktor:ktor-client-auth:2.3.4")
    val ktorVersion = "2.3.4"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-java:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:1.4.11")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

application.mainClass.set("raystark.llabassistant.MainKt")


kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}

val copyDocker = tasks.register<Copy>("copyDocker") {
    from("docker")
    into("build/docker")

    expand(
        hashMapOf("botToken" to file("BotToken").readText())
    )
}

val copyJar = tasks.register<Copy>("copyJar") {
    from(tasks.shadowJar.get().outputs)
    into("build/docker/l-lab-assistant")
}

tasks.register("dockerContext") {
    dependsOn(copyDocker, copyJar)
}

tasks.build.get().dependsOn("dockerContext")