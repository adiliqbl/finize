import Libraries.WorkManager.implementWorkManager

plugins {
	id("finize.module")
	id("plugins.hilt")
}

android {
	namespace = "com.adiliqbal.finize.sync"
}

dependencies {
	implementation(Libraries.Kotlin.Core)
	implementation(Libraries.Kotlin.Coroutines)

	implementWorkManager()
}