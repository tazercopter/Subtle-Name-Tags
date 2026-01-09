plugins {
    common
    id("net.fabricmc.fabric-loom-remap")
}

loom {
//    accessWidenerPath = commonNode.project.file("../../src/main/resources/${mod.id}-${mod.dep("minecraft")}.accesswidener")
}

dependencies {
    minecraft("com.mojang:minecraft:${mod.dep("minecraft")}")
    mappings(loom.layered { officialMojangMappings() })

    compileOnly("org.spongepowered:mixin:0.8.5")
    "io.github.llamalad7:mixinextras-common:0.3.5".let {
        compileOnly(it)
        annotationProcessor(it)
    }

    compileOnly("net.fabricmc:fabric-loader:${mod.dep("fabric")}")
}

val commonJava: Configuration by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = true
}

val commonResources: Configuration by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = true
}

artifacts {
    val mainSourceSet = sourceSets.main.get()

    mainSourceSet.java.sourceDirectories.files.forEach {
        add(commonJava.name, it)
    }

    mainSourceSet.resources.sourceDirectories.files.forEach {
        add(commonResources.name, it)
    }
}