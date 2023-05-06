@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("gamebook.android.application")
    id("gamebook.android.hilt")
}

android {

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
    //other
    implementation(libs.logging.timber)
}