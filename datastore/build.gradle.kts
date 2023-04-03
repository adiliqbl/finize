plugins {
	id("finize.module")
	id("plugins.hilt")
	id("plugins.serialization")
}

android {
	namespace = "com.adiliqbal.finize.datastore"
}

dependencies {
	implementation(project(":model"))
	implementation(project(":common"))

	implementation(Libraries.Data.Datastore)
}