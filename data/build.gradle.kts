plugins {
	id("finize.module")
	id("plugins.hilt")
	id("plugins.serialization")
}

android {
	namespace = "com.adiliqbal.finize.data"
}

dependencies {
	implementation(project(":model"))
	implementation(project(":common"))
	implementation(project(":database"))
	implementation(project(":datastore"))
	implementation(project(":network"))

	implementation(Libraries.Kotlin.Core)
	implementation(Libraries.Kotlin.Coroutines)

	implementation(Libraries.UI.Paging)

	testImplementation(project(":common:testing"))
}