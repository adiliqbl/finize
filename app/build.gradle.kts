plugins {
	id("finize-application")
	id("finize-application-compose")
	id("finize-hilt")
}

android {
	namespace = "com.adiliqbal.finize"

	defaultConfig {
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
	implementation(Dependencies.Kotlin.KtxCore)
}