plugins {
    id("com.android.library")
    // JetBrains
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.serializable)
    // Hilt
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.com.google.dagger.hilt.android)
}

android {
    namespace = "com.amarchaud.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }


    kotlin.sourceSets.configureEach {
        languageSettings.optIn("kotlin.RequiresOptIn")
    }


    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    buildFeatures {
        buildConfig = true
    }
}

kotlin {
    jvmToolchain(17)
}


dependencies {
    implementation(project(":domain"))

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.bundles.hilt.androidx)
    ksp(libs.hiltCompiler)

    // KTX
    implementation(libs.core.ktx)

    // Retrofit
    implementation(libs.bundles.api)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.roomCompiler)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // Kotlin DateTime
    implementation(libs.kotlinx.datetime)

    // Test
    testImplementation(libs.bundles.test)
}
