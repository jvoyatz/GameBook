pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GameBook"
include(":app")
include(":libraries:ui")
include(":libraries:mvvmi")
include(":libraries:shared")
include(":libraries:network")
include(":libraries:utils-android")
include(":libraries:testing")
include(":components:bets")
include(":features:gamebook")
