// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Android
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    // JetBrains
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.serializable) apply false
    // Hilt
    alias(libs.plugins.com.google.devtools.ksp) apply false
    alias(libs.plugins.com.google.dagger.hilt.android) apply false
    id("version-catalog")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
