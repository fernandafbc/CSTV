pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CSTV"
include(":app")
include(":data")
include(":data_remote")
include(":di")
include(":domain")
include(":navigation")
include(":uikit")
include(":matches_list")
include(":match_details")
