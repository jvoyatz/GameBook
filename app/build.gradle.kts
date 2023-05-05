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
    implementation(project(":libraries:ui"))
    implementation(libs.bundles.androidx.ui.common)
}