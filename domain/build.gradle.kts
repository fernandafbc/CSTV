plugins {
    id("kotlin")
    kotlin("kapt")
}

dependencies {
    implementation(libs.bundles.android.domainDependencies)
    testImplementation(libs.junit)
    testImplementation(libs.bundles.mockito)
}