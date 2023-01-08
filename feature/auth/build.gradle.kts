plugins {
	id("finize.feature")
}

android {
	namespace = "com.adiliqbal.finize.auth"
}

dependencies {
	implementation(Libraries.Google.PlayServices)
}