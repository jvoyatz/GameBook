package gr.jvoyatz.gamebook

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

/**
 * This extension method is used
 * to setup certain fields that are common to the Android Environment
 * when creating app or library modules.
 *
 * The catch here is that [com.android.build.gradle.LibraryExtension] extends CommonExtension
 * and that applies as well for the [com.android.build.api.dsl.ApplicationExtension],
 * meaning that we can invoke this method either from [AndroidLibConventionPlugin]
 * or [AndroidAppConventionPlugin]
 *
 * Using the code supplied in this method, we don't need to setup again and again
 * the same fields.
 */
@Suppress("UnstableApiUsage")
internal fun Project.configureAndroidCommon(
    extension: CommonExtension<*, *, *, *>,
) {
    extension.apply {
        compileSdk = extensions.getVersionCatalogExtension().getCompileSdk()

        defaultConfig {
            minSdk = extensions.getVersionCatalogExtension().getMinSdk()
            testInstrumentationRunner = extensions.getVersionCatalogExtension().getTestRunner()
        }

        buildFeatures {
            buildConfig = false
            viewBinding = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        packagingOptions {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
                resources.excludes.add("META-INF/*")
            }
        }
    }
}