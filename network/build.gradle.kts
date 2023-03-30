import Libraries.Firebase.implementFirebase

plugins {
	id("finize.module")
	id("plugins.hilt")
	id("finize.secrets")
	id("plugins.serialization")
}

android {
	namespace = "com.adiliqbal.finize.network"
}

secrets {
	propertiesFileName = Constants.SECRETS_FILE
	defaultPropertiesFileName = Constants.DEFAULT_SECRETS_FILE
}

dependencies {
	implementation(project(":common"))
	implementation(project(":model"))
	implementation(project(":datastore"))

	implementation(Libraries.Kotlin.Core)
	implementation(Libraries.Kotlin.Coroutines)

	implementFirebase()
	implementation(Libraries.Firebase.Firestore)
	implementation(Libraries.Firebase.Auth)
	implementation(Libraries.Firebase.Functions)

	implementation(Libraries.Network.Retrofit)
	implementation(Libraries.Network.OkHttpInterceptor)
	implementation(Libraries.Kotlin.JsonConvertor)

	testImplementation(project(":common:testing"))
}