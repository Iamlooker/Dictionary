plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
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
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
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
	implementation(project(":feature_dictionary:presentation"))

	implementation(Core.core)

	implementation(Compose.ui)
	implementation(Compose.foundation)
	implementation(Compose.material3)
	debugImplementation(Compose.toolingPreview)
	debugImplementation(Compose.tooling)

	implementation(Coroutines.core)
	implementation(Coroutines.android)

	implementation(Lifecycle.runtime)
	implementation(Lifecycle.composeViewModel)
}