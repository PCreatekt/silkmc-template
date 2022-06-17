import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id("fabric-loom") version "0.12-SNAPSHOT"
    id("io.github.juuxel.loom-quiltflower") version "1.7.2"
    id("org.quiltmc.quilt-mappings-on-loom") version "4.2.0"
}

group = "net.fabricmc.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.19")
    mappings(loom.layered {
        addLayer(quiltMappings.mappings("org.quiltmc:quilt-mappings:1.19+build.1:v2"))
        officialMojangMappings()
    })
    modImplementation("net.fabricmc:fabric-loader:0.14.8")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.55.3+1.19")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.8.0+kotlin.1.7.0")
    modImplementation("net.axay:fabrikmc-core:1.8.1")
    modImplementation("net.axay:fabrikmc-commands:1.8.1")
    modImplementation("net.axay:fabrikmc-igui:1.8.1")
    modImplementation("net.axay:fabrikmc-nbt:1.8.1")
    modImplementation("net.axay:fabrikmc-persistence:1.8.1")
    modImplementation("net.axay:fabrikmc-game:1.8.1")
    modImplementation("net.axay:fabrikmc-network:1.8.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
