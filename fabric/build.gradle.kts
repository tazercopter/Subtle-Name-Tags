plugins {
    loader
    id("net.fabricmc.fabric-loom-remap")
}

dependencies {
    minecraft("com.mojang:minecraft:${mod.dep("minecraft")}")

    mappings(loom.layered { officialMojangMappings() })

    modImplementation("net.fabricmc:fabric-loader:${mod.dep("fabric")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${mod.dep("fabric_api")}")
    include("folk.sisby:kaleido-config:${mod.dep("kaleido")}")
}

loom {
//    accessWidenerPath = commonNode.project.file("../../src/main/resources/${mod.id}-${mod.dep("minecraft")}.accesswidener")

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