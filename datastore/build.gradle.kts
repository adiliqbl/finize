import Libraries.Test.implementTesting

plugins {
	id("finize.module")
	id("finize.hilt")
	id("kotlinx-serialization")
}

android {
	namespace = "com.adiliqbal.finize.datastore"
}

dependencies {
	implementation(project(":model"))

	implementation(Libraries.Data.Datastore)
	implementation(Libraries.Kotlin.Json)

	implementTesting()
}