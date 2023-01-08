plugins {
	id("finize.module")
	id("finize.hilt")
}

android {
	namespace = "com.adiliqbal.finize.common"
}

dependencies {
	implementation(Libraries.Kotlin.Coroutines)

	testImplementation(project(":common:testing"))
}