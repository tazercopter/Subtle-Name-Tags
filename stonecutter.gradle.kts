plugins {
    id("dev.kikugie.stonecutter")
    // https://projects.neoforged.net/neoforged/ModDevGradle
    id("net.neoforged.moddev") version "2.0.134" apply false
    // https://fabricmc.net/develop
    id("net.fabricmc.fabric-loom") version "1.14-SNAPSHOT" apply false
}

stonecutter {
    parameters {
        filters.exclude("**/*.accesswidener")
        filters.exclude("**/*.classtweaker")

        replacements.string(current.parsed >= "26.1") {
            replace("ResourceLocation", "Identifier")
            replace("net.minecraft.Util", "net.minecraft.util.Util")
        }
    }
}

stonecutter active "26.1"