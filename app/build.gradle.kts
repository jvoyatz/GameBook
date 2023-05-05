@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("gamebook.android.application")
    id("gamebook.android.hilt")
}

android {

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions{
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    //implementation(project(":libraries:ui"))
    implementation(libs.bundles.androidx.ui.common)
}