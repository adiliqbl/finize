plugins {
	id("finize-application")
	id("finize-application-compose")
	id("kotlin-kapt")
}

android {
	namespace = "com.adiliqbal.finize"

	defaultConfig {
		applicationId = AppConfig.appId
		minSdk = AppConfig.minSdk
		targetSdk = AppConfig.targetSdk
		versionCode = AppConfig.versionCode
		versionName = AppConfig.versionName

		vectorDrawables { useSupportLibrary = true }

		javaCompileOptions {
			annotationProcessorOptions {
				arguments(mapOf("room.schemaLocation" to "$projectDir/schemas"))
			}
		}
	}

	packagingOptions {
		resources {
			excludes.add("/META-INF/{AL2.0,LGPL2.1}")
		}
	}
}

dependencies {
	implementation("androidx.core:core-ktx:${Versions.Kotlin.KtxCore}")
}