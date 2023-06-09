import gr.jvoyatz.gamebook.getLibs
import gr.jvoyatz.gamebook.getVersionCatalogExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
            }

            val libs = extensions.getVersionCatalogExtension().getLibs()
            target.dependencies {
                "implementation"(libs.findLibrary("coroutines").get())
                "implementation"(libs.findBundle("test").get())
            }
        }
    }
}