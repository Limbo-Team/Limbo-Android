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

rootProject.name = "LimboApp"
include(":app")
include(":core")
include(":auth")
include(":auth:auth_presentation")
include(":auth:auth_domain")
include(":dashboard")
include(":dashboard:dashboard_presentation")
include(":dashboard:dashboard_domain")
include(":dashboard:dashboard_data")
include(":quiz")
include(":quiz:quiz_data")
include(":quiz:quiz_domain")
include(":quiz:quiz_presentation")
include(":core_ui")
