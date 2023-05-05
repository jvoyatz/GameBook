package gr.jvoyatz.gamebook

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project

/**
 * We call this method from an `app` module
 */
internal fun Project.configureAndroidApp(
    extension: ApplicationExtension,
) {
    extension.apply {
        defaultConfig {
            targetSdk = extensions.getVersionCatalogExtension().getTargetSdk()
            applicationId = extensions.getVersionCatalogExtension().getPackageName()
            versionCode = extensions.getVersionCatalogExtension().getVersionCode()
            versionName = extensions.getVersionCatalogExtension().getVersionName()
            testInstrumentationRunner = extensions.getVersionCatalogExtension().getTestRunner()
        }
        namespace = extensions.getVersionCatalogExtension().getPackageName()

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
    }
}