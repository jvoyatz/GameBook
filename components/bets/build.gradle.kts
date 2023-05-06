@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("gamebook.android.library.plus")
}

android {
    namespace = "gr.jvoyatz.gamebook.components.bets"
}

dependencies {
    implementation(project(":libraries:shared"))
    implementation(project(":libraries:network"))


    //testing
    testImplementation(libs.bundles.test)
    testImplementation(project(":libraries:testing"))

    //android
    androidTestImplementation(libs.bundles.test.android)
    androidTestImplementation(libs.bundles.test)
}