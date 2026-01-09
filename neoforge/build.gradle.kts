plugins {
    loader
    id("net.neoforged.moddev")
    id("dev.kikugie.fletching-table.neoforge") version "0.1.0-alpha.22"
}

neoForge {
    // https://projects.neoforged.net/neoforged/neoforge
    version = mod.dep("neoforge")

    validateAccessTransformers = true

    runs {
        register("client") {
            client()
            ideName = "${mod.name} NeoForge Client"
        }
        register("server") {
            server()
            ideName = "${mod.name} NeoForge Server"
        }
    }

    mods {
        register(mod.id) {
            sourceSet(sourceSets.main.get())
        }
    }
}

dependencies {
    jarJar("folk.sisby:kaleido-config:${mod.dep("kaleido")}")
    if (stonecutter.eval(stonecutter.current.version, "<26.1")) {
        "additionalRuntimeClasspath"("folk.sisby:kaleido-config:${mod.dep("kaleido")}")
    }
}

//fletchingTable {
//    accessConverter.register(sourceSets.main) {
//        add("${mod.id}-1.21.1.accesswidener")
//    }
//}

tasks.named("createMinecraftArtifacts") {
    dependsOn(":neoforge:${stonecutter.current.version}:processResources")
}