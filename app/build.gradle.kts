plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id(Hilt.plugin)
}

android {
    compileSdk = Android.targetSdk

    defaultConfig {
        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
        all {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        compose = true
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompiler
    }

    packagingOptions {
        resources {
            excludes += Excludes.exclude
        }
    }
}

dependencies {
    implementation(AndroidX.activity)
    implementation(Accompanist.insets)

    implementation(Core.core)

    implementation(Compose.ui)
    implementation(Compose.foundation)
    implementation(Compose.material3)
    implementation(Compose.toolingPreview)
    implementation(Compose.activity)
    debugImplementation(Compose.tooling)

    implementation(Lifecycle.runtime)
    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.composeViewModel)

    implementation(Coroutines.core)
    implementation(Coroutines.android)

    kapt(Hilt.compiler)
    implementation(Hilt.android)

    implementation(OkHttp.core)
    implementation(OkHttp.logger)

    implementation(Retrofit.core)
    implementation(Retrofit.gson)

    implementation(Room.runtime)
    implementation(Room.ktx)
    kapt(Room.compiler)
}

kapt {
    correctErrorTypes = true
}