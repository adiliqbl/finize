import Libraries.Firebase.implementFirebase

plugins {
	id("finize.application")
	id("finize.application-compose")
	id("plugins.hilt")
}

android {
	namespace = "com.adiliqbal.finize"

	packagingOptions {
		resources {
			excludes.add("/META-INF/{AL2.0,LGPL2.1}")
		}
	}
}

dependencies {
	implementation(project(":common"))
	implementation(project(":data"))
	implementation(project(":database"))
	implementation(project(":datastore"))
	implementation(project(":model"))
	implementation(project(":navigation"))
	implementation(project(":network"))
	implementation(project(":ui"))

	implementation(project(":feature:accounts"))
	implementation(project(":feature:analytics"))
	implementation(project(":feature:auth"))
	implementation(project(":feature:budgets"))
	implementation(project(":feature:home"))
	implementation(project(":feature:investments"))
	implementation(project(":feature:profile"))
	implementation(project(":feature:templates"))
	implementation(project(":feature:transactions"))

	implementation(Libraries.Kotlin.Core)
	implementation(Libraries.UI.Splashscreen)

	implementFirebase()
	implementation(Libraries.Firebase.Crashlytics)
}