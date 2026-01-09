import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType


val Project.stonecutterBuild get() = extensions.getByType<StonecutterBuildExtension>()
val Project.mod: Data get() = Data(rootProject.project(stonecutterBuild.current.project))

fun Project.find(key: String): String = requireNotNull(findProperty(key)) { "Missing $key" } .toString()

val Project.commonNode get() = requireNotNull(stonecutterBuild.node.sibling("common"))

@JvmInline
value class Data(private val project: Project) {
    val group: String get() = project.find("mod.group")
    val id: String get() = project.find("mod.id")
    val name: String get() = project.find("mod.name")
    val version: String get() = project.find("mod.version")

    fun dep(key: String): String = project.find("deps.$key")
}