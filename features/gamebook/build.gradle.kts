@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("gamebook.android.library.plus")
    alias(libs.plugins.org.jetbrains.kotlin.parcelize)
}

android {
    namespace = "gr.jvoyatz.gamebook.features.sportsbook"
}

dependencies {
    //android
    implementation(libs.bundles.androidx.ui.common)
    implementation(libs.core.ktx)
    //modules
    implementation(project(":libraries:mvvmi"))
    implementation(project(":libraries:ui"))
    implementation(project(":components:bets"))
    implementation(project(":libraries:shared"))
    implementation(project(":libraries:utils-android"))

    //test
    testImplementation(project(":libraries:testing"))
}