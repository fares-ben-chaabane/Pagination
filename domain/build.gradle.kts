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
    namespace = "com.amarchaud.domain"
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

kotlin {
    jvmToolchain(17)
}


dependencies {
    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.bundles.hilt.androidx)
    ksp(libs.hiltCompiler)

    // Compose Paging
    implementation(libs.compose.paging)

    // KTX
    implementation(libs.core.ktx)

    // Test
    testImplementation(libs.bundles.test)
}
