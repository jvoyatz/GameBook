@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("gamebook.android.library")
}

android {
    namespace = "gr.jvoyatz.gamebook.libraries.testing"
}

dependencies {
    implementation(libs.bundles.test.android)
    implementation(libs.bundles.test)

    implementation(libs.bundles.networking)
    implementation(project(":libraries:utils-android"))
}