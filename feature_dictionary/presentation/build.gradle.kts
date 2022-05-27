plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	kotlin("kapt")
	id(Hilt.plugin)
}

android {
	compileSdk = Android.targetSdk

	defaultConfig {
		minSdk = Android.minSdk
		targetSdk = Android.targetSdk

		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
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
}

dependencies {
	implementation(project(":core"))
	implementation(project(":feature_dictionary:domain"))
	implementation(project(":feature_dictionary:data"))

	implementation(Core.core)

	implementation(Compose.ui)
	implementation(Compose.foundation)
	implementation(Compose.material3)

	implementation(Coroutines.core)
	implementation(Coroutines.android)

	kapt(Hilt.compiler)
	implementation(Hilt.android)

	implementation(Lifecycle.runtime)
	implementation(Lifecycle.viewModel)
}