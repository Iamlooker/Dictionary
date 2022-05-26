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
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
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
	implementation(project(":core"))
	implementation(project(":feature_dictionary:di"))
	implementation(project(":feature_dictionary:domain"))
	implementation(project(":feature_dictionary:data"))

	implementation(Accompanist.insets)

	implementation(AndroidX.activityCompose)

	implementation(Core.core)

	implementation(Compose.ui)
	implementation(Compose.foundation)
	implementation(Compose.material3)
	debugImplementation(Compose.toolingPreview)
	debugImplementation(Compose.tooling)

	implementation(Lifecycle.runtime)
	implementation(Lifecycle.viewModel)
	implementation(Lifecycle.composeViewModel)

	implementation(Coroutines.core)
	implementation(Coroutines.android)

	kapt(Hilt.compiler)
	implementation(Hilt.android)
}

kapt {
	correctErrorTypes = true
}