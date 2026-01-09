plugins {
    common
    id("net.neoforged.moddev")
}

neoForge {
    // https://projects.neoforged.net/neoforged/neoform
    neoFormVersion = mod.dep("neoform")
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
    "io.github.llamalad7:mixinextras-common:0.3.5".let {
        compileOnly(it)
        annotationProcessor(it)
    }
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