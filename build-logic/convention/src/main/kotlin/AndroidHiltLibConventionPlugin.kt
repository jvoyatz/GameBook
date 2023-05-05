import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Replaces the plugin `com.android.library' in Android library modules
 */
class AndroidHiltLibConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("gamebook.android.library")
                apply("gamebook.android.hilt")
            }
        }
    }
}