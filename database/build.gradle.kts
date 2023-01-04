import Libraries.Test.implementAndroidTesting

plugins {
	id("finize.module")
	id("finize.hilt")
	id("kotlinx-serialization")
}

android {
	namespace = "com.adiliqbal.finize.database"

	defaultConfig {
		// The schemas directory contains a schema file for each version of the Room database.
		// This is required to enable Room auto migrations.
		// See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
		javaCompileOptions {
			annotationProcessorOptions {
				argument("room.schemaLocation", "$projectDir/schemas")
			}
		}
	}

	sourceSets {
		getByName("androidTest").assets.srcDirs("$projectDir/schemas")
	}
}

dependencies {
	implementation(project(":model"))

	implementation(Libraries.Kotlin.Core)
	implementation(Libraries.Kotlin.Coroutines)

	implementation(Libraries.Room.Room)
	implementation(Libraries.Room.Runtime)
	implementation(Libraries.Room.Paging)
	kapt(Libraries.Room.Compiler)

	implementation(Libraries.Kotlin.Json)

	implementAndroidTesting()
	androidTestImplementation(Libraries.Room.Testing)
}