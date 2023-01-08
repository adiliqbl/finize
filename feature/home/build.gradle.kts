plugins {
	id("finize.feature")
}

android {
	namespace = "com.adiliqbal.finize.home"
}

dependencies {
	implementation(project(":feature:accounts"))
	implementation(project(":feature:budgets"))
	implementation(project(":feature:transactions"))
	implementation(project(":feature:templates"))
	implementation(project(":feature:analytics"))
}