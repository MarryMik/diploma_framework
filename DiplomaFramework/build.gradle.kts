plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.seleniumhq.selenium:selenium-java:4.21.0")
    implementation("log4j:log4j:1.2.16")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.0.1")
    implementation("org.openpnp:opencv:4.9.0-0")
    implementation("org.json:json:20090211")
}

tasks.test {
    useJUnitPlatform()
}