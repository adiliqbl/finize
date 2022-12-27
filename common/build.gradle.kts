import Libraries.Test.implementTest

plugins {
	id("finize.module")
}

android {
	namespace = "com.adiliqbal.finize.common"
}

dependencies {
	implementation(Libraries.Kotlin.Core)
	implementation(Libraries.Kotlin.Coroutines)

	implementTest()
}