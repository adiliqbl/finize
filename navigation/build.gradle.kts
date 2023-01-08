import Libraries.Navigation.implementNavigation

plugins {
	id("finize.module")
	id("plugins.compose")
}

android {
	namespace = "com.adiliqbal.finize.navigation"
}

dependencies {
	implementNavigation("api")
}