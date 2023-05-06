import java.io.FileInputStream
import java.util.Properties

plugins {
    id("gamebook.android.library.plus")
}

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "gr.jvoyatz.gamebook.libraries.network"

    defaultConfig {
        buildConfigField("String", "HOST", prop.getProperty("HOST"))
        buildConfigField("String", "TOKEN", prop.getProperty("TOKEN"))
    }
    @Suppress("UnstableApiUsage")
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":libraries:utils-android"))
    implementation(project(":libraries:testing"))
    implementation(libs.bundles.networking)
    kapt(libs.moshi.codegen)
}