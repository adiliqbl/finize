plugins {
	id("finize.module")
	id("plugins.hilt")
}

android {
	namespace = "com.adiliqbal.finize.common"
}

dependencies {
	implementation(Libraries.Kotlin.Coroutines)

	testImplementation(project(":common:testing"))
}