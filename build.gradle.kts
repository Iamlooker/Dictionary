buildscript {
	repositories {
		google()
		mavenCentral()
	}
	dependencies {
		classpath(Classpath.gradleClasspath)
		classpath(Classpath.gradleKotlin)
		classpath(Hilt.classpath)
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
	}
}

tasks.register("clean", Delete::class) {
	delete(rootProject.buildDir)
}