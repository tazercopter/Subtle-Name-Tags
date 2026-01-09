plugins {
    id("java")
}

version = "${mod.version}+${stonecutterBuild.current.version}-${stonecutterBuild.node.branch.id}"
base.archivesName = mod.id

tasks.processResources {
    dependsOn("stonecutterGenerate")

    var expandProps = mapOf(
        "group" to mod.group,
        "id" to mod.id,
        "name" to mod.name,
        "version" to mod.version,
        "minecraft" to mod.dep("minecraft"),
        "java" to mod.dep("java")
    )

    filesMatching(listOf(
        "pack.mcmeta",
        "*.mixins.json",
        "fabric.mod.json",
        "META-INF/neoforge.mods.toml"
    )) {
        expand(expandProps);
    }

    inputs.properties(expandProps)
}

tasks.named("processResources") {
    dependsOn(":common:${stonecutterBuild.current.version}:stonecutterGenerate")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(mod.dep("java"))
    // withSourcesJar()
    // withJavadocJar()
}

repositories {
    exclusiveContent {
        forRepository {
            maven("https://repo.spongepowered.org/repository/maven-public") {
                name = "Sponge"
            }
        }

        filter {
            includeGroupAndSubgroups("org.spongepowered")
        }
    }

    exclusiveContent {
        forRepository {
            maven("https://repo.sleeping.town/") {
                name = "Sisby Maven"
            }
        }

        filter {
            includeGroupAndSubgroups("folk.sisby")
        }
    }
}

dependencies {
    implementation("folk.sisby:kaleido-config:${mod.dep("kaleido")}")
}