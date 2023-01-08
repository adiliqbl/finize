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
	implementCompose("api")
	implementMaterialDesign("api")
	api(Libraries.UI.Coil)
}