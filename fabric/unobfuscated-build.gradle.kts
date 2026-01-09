plugins {
    loader
    id("net.fabricmc.fabric-loom")
}

dependencies {
    minecraft("com.mojang:minecraft:${mod.dep("minecraft")}")

    implementation("net.fabricmc:fabric-loader:${mod.dep("fabric")}")
    implementation("net.fabricmc.fabric-api:fabric-api:${mod.dep("fabric_api")}")
    include("folk.sisby:kaleido-config:${mod.dep("kaleido")}")
}

loom {
//    accessWidenerPath = commonNode.project.file("../../src/main/resources/${mod.id}-${mod.dep("minecraft")}.classtweaker")

    mixin {
        defaultRefmapName.set("${mod.id}.refmap.json")
    }

    runs {
        getByName("client") {
            client()
            configName = "${mod.name} Fabric Client"
            ideConfigGenerated(true)
        }
        getByName("server") {
            server()
            configName = "${mod.name} Fabric Server"
            ideConfigGenerated(true)
        }
    }
}