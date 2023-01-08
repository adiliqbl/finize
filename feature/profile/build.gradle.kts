plugins {
	id("finize.feature")
}

android {
	namespace = "com.adiliqbal.finize.profile"
}

dependencies {
	implementation(project(":feature:templates"))
}