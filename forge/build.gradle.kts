plugins {
    loader
    id("net.neoforged.moddev.legacyforge")
}

legacyForge {
    version = mod.dep("forge")

    validateAccessTransformers = true

    runs {
        register("client") {
            client()
            ideName = "${mod.name} Forge Client"
        }
        register("server") {
            server()
            ideName = "${mod.name} Forge Server"
        }
    }

    mods {
        register(mod.id) {
            sourceSet(sourceSets.main.get())
        }
    }
}

mixin {
    config("${mod.id}.mixins.json")
    config("${mod.id}.forge.mixins.json")
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")

    "io.github.llamalad7:mixinextras-common:0.5.0".let {
        compileOnly(it)
        annotationProcessor(it)
    }

    jarJar("folk.sisby:kaleido-config:${mod.dep("kaleido")}")
    "additionalRuntimeClasspath"("folk.sisby:kaleido-config:${mod.dep("kaleido")}")
}

tasks.named("createMinecraftArtifacts") {
    dependsOn(":forge:${stonecutter.current.version}:processResources")
}