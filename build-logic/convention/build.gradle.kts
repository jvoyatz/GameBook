plugins {
    `kotlin-dsl`
}

group = "${libs.versions.packageName}.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    //compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {

    //here we register the custom plugins that will be used throught out main project's modules
    // the main purpose is to move certain dependencies declared in a module in a specific plugin either a library module
    // or a feature module
    //this gives us an extra capability of having single source of truth for the dependencies needed when creating a new module
    plugins {

        register("androidApplication") {
            id = "gamebook.android.application"
            implementationClass = "AndroidAppConventionPlugin"
        }
        register("androidKotlin") {
            id = "gamebook.jetbrains.kotlin.android"
            implementationClass = "AndroidKotlinConventionPlugin"
        }
        register("androidLibrary") {
            id = "gamebook.android.library"
            implementationClass = "AndroidLibConventionPlugin"
        }
        register("androidHilt") {
            id = "gamebook.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidHiltLib") {
            id = "gamebook.android.library.plus"
            implementationClass = "AndroidHiltLibConventionPlugin"
        }
        register("kotlinModule") {
            id = "gamebook.kotlin"
            implementationClass = "KotlinConventionPlugin"
        }
    }
}
