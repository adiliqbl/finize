import Libraries.Test.implementTest

plugins {
	id("finize.module")
	id("finize.hilt")
}

android {
	namespace = "com.adiliqbal.finize.data"
}

dependencies {
	implementation(project(":model"))
	implementation(project(":database"))
	implementation(project(":datastore"))
	implementation(project(":network"))

	implementation(Libraries.Kotlin.Core)
	implementation(Libraries.Kotlin.Coroutines)

	implementTest()
}