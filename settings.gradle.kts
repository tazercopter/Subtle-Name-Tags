pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.fabricmc.net") { name = "Fabric" }
        maven("https://maven.neoforged.net/releases/") { name = "NeoForged" }
        maven("https://maven.minecraftforge.net") { name = "Forge" }
        maven("https://maven.kikugie.dev/releases") { name = "KikuGie" }
        maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
        maven("https://repo.spongepowered.org/repository/maven-public") { name = "Sponge" }
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.8"
}

rootProject.name = "Subtle-Name-Tags"

stonecutter {
    create(rootProject) {
        versions("26.1", "1.21.1", "1.20.1")

        branch("common") {
            version("1.20.1").buildscript("loom-build.gradle.kts")
            versions("26.1", "1.21.1")
        }
        branch("fabric") {
            version("26.1").buildscript("unobfuscated-build.gradle.kts")
            versions("1.21.1", "1.20.1")
        }
        branch("neoforge") {
            versions("26.1", "1.21.1")
        }
        branch("forge") {
            version("1.20.1")
        }
    }
}