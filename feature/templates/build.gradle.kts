plugins {
	id("finize.feature")
}

android {
	namespace = "com.adiliqbal.finize.templates"
}

dependencies {
	implementation(project(":feature:transactions"))
}