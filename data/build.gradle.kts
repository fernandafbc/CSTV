@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("kotlin")
    kotlin("kapt")
}

dependencies {
    api(project(path = ":domain"))

    implementation(libs.bundles.android.domainDependencies)
}