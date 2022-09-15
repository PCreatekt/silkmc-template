import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val silkVersion = "1.9.2"


plugins {
    kotlin("jvm") version "1.7.10"
    id("fabric-loom") version "0.12-SNAPSHOT"
    id("io.github.juuxel.loom-quiltflower") version "1.7.3"
    id("org.quiltmc.quilt-mappings-on-loom") version "4.2.1"
}

group = "net.fabricmc.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.19.2")
    mappings(loom.layered {
        addLayer(quiltMappings.mappings("org.quiltmc:quilt-mappings:1.19.2+build.14:v2"))
        officialMojangMappings()
    })
    modImplementation("net.fabricmc:fabric-loader:0.14.9")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.61.0+1.19.2")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.8.3+kotlin.1.7.10")
    modImplementation("net.silkmc:silk-core:$silkVersion")
    modImplementation("net.silkmc:silk-commands:$silkVersion")
    modImplementation("net.silkmc:silk-igui:$silkVersion")
    modImplementation("net.silkmc:silk-nbt:$silkVersion")
    modImplementation("net.silkmc:silk-persistence:$silkVersion")
    modImplementation("net.silkmc:silk-game:$silkVersion")
    modImplementation("net.silkmc:silk-network:$silkVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs += "-Xskip-prerelease-check"
        }    }
    withType<JavaCompile> {
        options.release.set(17)
    }
}

extra["kotlin.code.style"] = "official"
