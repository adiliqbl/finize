import Libraries.Compose.implementCompose
import Libraries.UI.implementMaterialDesign

plugins {
	id("finize.module")
	id("finize.compose")
}

android {
	namespace = "com.adiliqbal.finize.ui"
}

dependencies {
	api(project(":navigation"))

	implementCompose("api")
	implementMaterialDesign("api")
	api(Libraries.UI.Lifecycle)
	api(Libraries.UI.Splashscreen)
	api(Libraries.UI.Coil)
	api(Libraries.UI.Paging)
}