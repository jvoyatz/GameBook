import gr.jvoyatz.gamebook.getPackageName
import gr.jvoyatz.gamebook.getVersionCatalogExtension

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("gamebook.android.application")
    id("gamebook.android.hilt")
}

android {
    val packageName = extensions.getVersionCatalogExtension().getPackageName()
    namespace = packageName

    @Suppress("UnstableApiUsage")
    buildFeatures {
        buildConfig = true
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}
kapt {
    correctErrorTypes = true
}

dependencies {
    //android
    implementation(libs.bundles.androidx.ui.common)
    implementation(libs.bundles.androidx.navigation)
    //modules
    implementation(project(":libraries:ui"))
    implementation(project(":features:gamebook"))
    //other
    implementation(libs.logging.timber)
}