plugins {
	id("finize.application")
	id("finize.application-compose")
	id("finize.hilt")
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
	implementation(project(":data"))
	implementation(project(":database"))
	implementation(project(":datastore"))
	implementation(project(":model"))
	implementation(project(":navigation"))
	implementation(project(":network"))
	implementation(project(":sync"))
	implementation(project(":ui"))

	implementation(Libraries.Kotlin.Core)
}