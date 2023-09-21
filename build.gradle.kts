plugins {
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

application {
    mainClass = "raystark.llabassistant.Main"
}

tasks.test {
    useJUnitPlatform()
}