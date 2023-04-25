import Libraries.Firebase.implementFirebase

plugins {
	id("finize.feature")
	id("finize.secrets")
}

android {
	namespace = "com.adiliqbal.finize.auth"
}

secrets {
	propertiesFileName = Constants.SECRETS_FILE
	defaultPropertiesFileName = Constants.DEFAULT_SECRETS_FILE
}

dependencies {
	implementFirebase()
	implementation(Libraries.Google.PlayServices)
	implementation(Libraries.Firebase.Auth)
}