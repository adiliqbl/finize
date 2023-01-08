import Libraries.Hilt.implementHiltTesting
import Libraries.Test.implementAndroidTesting
import Libraries.Test.implementTesting

plugins {
	id("finize.module")
	id("plugins.hilt")
}

android {
	namespace = "com.adiliqbal.finize.testing"
}

dependencies {
	implementation(project(":common"))
	implementation(project(":data"))
	implementation(project(":model"))

	implementTesting("api", true)
	implementAndroidTesting("api", true)
	implementHiltTesting("api", true)
}