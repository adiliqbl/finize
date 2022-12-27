import Libraries.Test.implementTest

plugins {
	id("finize.module")
	id("finize.hilt")
}

android {
	namespace = "com.adiliqbal.finize.datastore"
}

dependencies {
	implementation(Libraries.Data.Datastore)

	implementTest()
}