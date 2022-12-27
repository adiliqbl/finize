import Libraries.Test.implementTesting

plugins {
	id("finize.module")
}

android {
	namespace = "com.adiliqbal.finize.common"
}

dependencies {
	implementation(Libraries.Kotlin.Core)
	implementation(Libraries.Kotlin.Coroutines)

	implementTesting()
}