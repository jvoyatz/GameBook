@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("gamebook.android.library.plus")
}

android {
    namespace = "gr.jvoyatz.gamebook.features.sportsbook"
}

dependencies {
    //android
    implementation(libs.bundles.androidx.ui.common)
}