dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "Dictionary"
include(":app")
include(":feature_dictionary:data")
include(":feature_dictionary:domain")
include(":core")
include(":feature_dictionary:di")
include(":feature_dictionary:presentation")
include(":feature_dictionary:ui")
