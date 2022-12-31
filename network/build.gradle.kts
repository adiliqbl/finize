import Libraries.Firebase.implementFirebase
import Libraries.Test.implementTesting

plugins {
	id("finize.module")
	id("finize.hilt")
	id("finize.secrets")
	id("kotlinx-serialization")
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
	implementation(Libraries.Firebase.Messaging)
	implementation(Libraries.Network.Retrofit)
	implementation(Libraries.Network.OkHttpInterceptor)
	implementation(Libraries.Kotlin.Json)
	implementation(Libraries.Kotlin.JsonConvertor)

	implementTesting()
}