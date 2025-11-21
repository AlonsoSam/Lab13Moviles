pluginManagement {
    repositories {
        // gradlePluginPortal primero para asegurar resolución de plugins/kotlin/compose cuando se usan aliases
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    // opcional: si usas settings con version catalog, no es necesario otro bloque aquí
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "lab13"
include(":app")
