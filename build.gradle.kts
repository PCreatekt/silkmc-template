import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id("fabric-loom") version "0.12-SNAPSHOT"
    id("io.github.juuxel.loom-quiltflower") version "1.7.3"
    id("org.quiltmc.quilt-mappings-on-loom") version "4.2.0"
    id("com.google.devtools.ksp") version "1.7.0-1.0.6" apply false
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev725" apply false
}

group = "net.fabricmc.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven("https://androidx.dev/storage/compose-compiler/repository")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    minecraft("com.mojang:minecraft:1.19")
    mappings(loom.layered {
        addLayer(quiltMappings.mappings("org.quiltmc:quilt-mappings:1.19+build.1:v2"))
        officialMojangMappings()
    })
    modImplementation("net.fabricmc:fabric-loader:0.14.8")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.56.3+1.19")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.8.0+kotlin.1.7.0")
    modImplementation("net.silkmc:silk-core:1.9.0")
    modImplementation("net.silkmc:silk-commands:1.9.0")
    modImplementation("net.silkmc:silk-igui:1.9.0")
    modImplementation("net.silkmc:silk-nbt:1.9.0")
    modImplementation("net.silkmc:silk-persistence:1.9.0")
    modImplementation("net.silkmc:silk-game:1.9.0")
    modImplementation("net.silkmc:silk-network:1.9.0")
    modImplementation("net.silkmc:silk-compose:1.0.0") {
        attributes {
            attribute(Attribute.of("ui", String::class.java), "awt")
        }
    }
}

configurations.all {
    attributes {
        attribute(Attribute.of("ui", String::class.java), "awt")
    }
    resolutionStrategy.dependencySubstitution {
        substitute(module("org.jetbrains.compose.compiler:compiler")).apply {
            using(module("androidx.compose.compiler:compiler:1.2.0-dev-k1.7.0-53370d83bb1"))
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
    withType<JavaCompile> {
        options.release.set(17)
    }
}

extra["kotlin.code.style"] = "official"
