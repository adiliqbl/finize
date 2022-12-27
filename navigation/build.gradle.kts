import Libraries.Navigation.implementNavigation

plugins {
	id("finize.module")
	id("finize.compose")
}

android {
	namespace = "com.adiliqbal.finize.navigation"
}

dependencies {
	implementNavigation("api")
}