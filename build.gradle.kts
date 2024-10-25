plugins {
    id("java")
}

group = "com.am4m.loottable"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-adapters:1.15.1")
}