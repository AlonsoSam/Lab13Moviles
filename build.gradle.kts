// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // aliases from libs.versions.toml (version catalog)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    // No se suelen necesitar entradas aquí cuando se usa plugins DSL + version catalog,
    // pero dejamos repositorios explícitos por seguridad.
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
