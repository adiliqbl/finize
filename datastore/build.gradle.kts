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

	implementation(Libraries.Data.Datastore)
}