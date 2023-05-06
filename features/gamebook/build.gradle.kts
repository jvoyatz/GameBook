@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("gamebook.android.library.plus")
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "gr.jvoyatz.gamebook.features.sportsbook"
}

dependencies {
    //android
    implementation(libs.bundles.androidx.ui.common)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
}