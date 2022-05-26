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
}

dependencies {
	implementation(project(":feature_dictionary:data"))
	implementation(project(":feature_dictionary:domain"))

	implementation(Core.core)

	kapt(Hilt.compiler)
	implementation(Hilt.android)

	implementation(Retrofit.core)
	implementation(Retrofit.gson)

	implementation(Room.runtime)
	implementation(Room.ktx)
	kapt(Room.compiler)
}