import Libraries.WorkManager.implementWorkManager

plugins {
	id("finize.module")
	id("finize.hilt")
}

android {
	namespace = "com.adiliqbal.finize.sync"
}

dependencies {
	implementWorkManager()

	implementation(Libraries.Kotlin.Core)
	implementation(Libraries.Kotlin.Coroutines)
}