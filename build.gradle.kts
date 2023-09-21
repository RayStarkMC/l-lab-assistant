plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "raystark"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.discord4j:discord4j-core:3.2.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

application.mainClass.set("raystark.llabassistant.Main")


kotlin {
    jvmToolchain(20)
}

tasks.test {
    useJUnitPlatform()
}