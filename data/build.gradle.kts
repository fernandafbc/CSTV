@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("kotlin")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    kotlin("kapt")
}

dependencies {
    api(project(path = ":domain"))
}