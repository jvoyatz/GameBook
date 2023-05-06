@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("gamebook.android.library")
    alias(libs.plugins.org.jetbrains.kotlin.parcelize)
}

android {
    namespace = "com.jvoyatz.gamebook.libraries.mvvmi"
}

dependencies {
    implementation(libs.coroutines)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.savedState)
}