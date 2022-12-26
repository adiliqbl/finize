import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

	object Build {
		const val GoogleService = "com.google.gms:google-services:${Versions.Build.GoogleService}"
	}

	object Kotlin {
		const val KtxCore = "androidx.core:core-ktx:${Versions.Kotlin.KtxCore}"
		const val Coroutines =
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.Coroutines}"
		const val DateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.Kotlin.DateTime}"
		const val JsonGradlePlugin =
			"org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.Kotlin}"
		const val Json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.Json}"
		const val JsonConvertor =
			"com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.Kotlin.JsonConvertor}"
	}

	object Firebase {
		const val BOM = "com.google.firebase:firebase-bom:${Versions.Firebase.BOM}"
		const val Crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
		const val CrashlyticsGradlePlugin =
			"com.google.firebase:firebase-crashlytics-gradle:${Versions.Firebase.Crashlytics}"
	}

	object Hilt {
		private const val Hilt = "com.google.dagger:hilt-android:${Versions.Hilt.Hilt}"
		const val GradlePlugin =
			"com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.Hilt}"
		private const val Compiler = "com.google.dagger:hilt-compiler:${Versions.Hilt.Hilt}"
		internal const val Navigation =
			"androidx.hilt:hilt-navigation-compose:${Versions.Hilt.Navigation}"
		private const val WorkManager = "androidx.hilt:hilt-work:${Versions.Hilt.WorkManager}"
		private const val WorkManagerCompiler =
			"androidx.hilt:hilt-compiler:${Versions.Hilt.WorkManager}"

		fun DependencyHandler.implementHilt() {
			add("implementation", Hilt)
			add("kapt", Compiler)
		}

		fun DependencyHandler.implementWorkManager(type: String = "implementation") {
			add(type, WorkManager)
			add(type, WorkManagerCompiler)
		}
	}

	object Compose {
		private const val Compose = "androidx.compose.ui:ui:${Versions.Compose.Compose}"
		private const val Foundation =
			"androidx.compose.foundation:foundation:${Versions.Compose.Compose}"
		private const val ViewModel =
			"androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.UI.Lifecycle}"
		private const val Lifecycle =
			"androidx.lifecycle:lifecycle-runtime-compose:${Versions.UI.Lifecycle}"
		private const val Activity =
			"androidx.activity:activity-compose:${Versions.Compose.Activity}"
		private const val Compiler = "1.3.1"
		const val Paging = "androidx.paging:paging-compose:${Versions.Compose.Paging}"
		private const val Preview =
			"androidx.compose.ui:ui-tooling-preview${Versions.Compose.Compose}"
		private const val PreviewTool = "androidx.compose.ui:ui-tooling:${Versions.Compose.Compose}"
		private const val TestManifest =
			"androidx.compose.ui:ui-test-manifest:${Versions.Compose.Compose}"
		private const val TestJUnit =
			"androidx.compose.ui:ui-test-junit4:${Versions.Compose.Compose}"

		fun DependencyHandler.implementCompose(type: String = "implementation") {
			add(type, Compose)
			add(type, Foundation)
			add(type, Preview)
			add(type, PreviewTool)
			add(type, Compiler)
			add(type, ViewModel)
			add(type, Lifecycle)
			add(type, Activity)
		}

		fun DependencyHandler.implementComposeTest() {
			add("implementation", TestManifest)
			add("implementation", TestJUnit)
		}
	}

	object Navigation {
		private const val Navigation =
			"androidx.navigation:navigation-compose:${Versions.Navigation.Navigation}"
		private const val Animation =
			"com.google.accompanist:accompanist-navigation-animation:${Versions.Navigation.Animation}"

		fun DependencyHandler.implementNavigation(type: String = "implementation") {
			add(type, Navigation)
			add(type, Hilt.Navigation)
			add(type, Animation)
		}
	}

	object UI {
		private const val Material3 =
			"androidx.compose.material3:material3:${Versions.UI.Material3}"
		private const val Icons =
			"androidx.compose.material:material-icons-extended:${Versions.Compose.Compose}"
		const val Splashscreen = "androidx.core:core-splashscreen:${Versions.UI.Splashscreen}"
		const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.UI.Lifecycle}"
		const val Coil = "io.coil-kt:coil-compose:${Versions.UI.Coil}"

		fun DependencyHandler.implementMaterialDesign(type: String = "implementation") {
			add(type, Material3)
			add(type, Icons)
		}
	}

	object Room {
		private const val Room = "androidx.room:room-ktx:${Versions.Data.Room}"
		private const val Runtime = "androidx.room:room-runtime:${Versions.Data.Room}"
		private const val Compiler = "androidx.room:room-compiler:${Versions.Data.Room}"
		private const val Paging = "androidx.room:room-paging:${Versions.Data.Room}"
		private const val Testing = "androidx.room:room-testing:${Versions.Data.Room}"

		fun DependencyHandler.implementRoom(type: String = "implementation") {
			add(type, Room)
			add(type, Runtime)
			add(type, Compiler)
			add(type, Paging)
			add(type, Testing)
		}
	}

	object Data {
		private const val Datastore =
			"androidx.datastore:datastore-preferences:${Versions.Data.Datastore}"

		fun DependencyHandler.implementDatastore(type: String = "implementation") {
			add(type, Datastore)
		}
	}

	object WorkManager {
		const val WorkManager = "androidx.work:work-runtime-ktx:${Versions.Data.WorkManager}"
		const val Testing = "androidx.work:work-testing:${Versions.Data.WorkManager}"
	}

	object Network {
		const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}"
		const val OkHttpInterceptor =
			"com.squareup.okhttp3:logging-interceptor:${Versions.Network.OkHttp}"
	}

	object Util {
		const val Timber = "com.jakewharton.timber:timber:${Versions.Util.Timber}"
		const val Inject = "javax.inject:javax.inject:${Versions.Util.Inject}"
	}

	object Test {
		private const val JUnit = "junit:junit:${Versions.Test.JUnit}"
		private const val AndroidJUnit = "androidx.test.ext:junit:${Versions.Test.AndroidJUnit}"
		private const val AndroidCore = "androidx.test:core:${Versions.Test.AndroidCore}"
		private const val AndroidCoreKtx = "androidx.test:core-ktx:${Versions.Test.AndroidCore}"
		private const val CoroutineTest =
			"org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.CoroutineTest}"
		private const val Mockito = "org.mockito:mockito-core:${Versions.Test.Mockito}"
		private const val MockitoKotlin =
			"org.mockito.kotlin:mockito-kotlin:${Versions.Test.MockitoKotlin}"

		fun DependencyHandler.implementTest() {
			implementUnitTest()
			implementAndroidTest()
			implementMockito()
		}

		private fun DependencyHandler.implementUnitTest() {
			add("implementation", JUnit)
			add("implementation", AndroidJUnit)
			add("implementation", CoroutineTest)
		}

		private fun DependencyHandler.implementAndroidTest() {
			add("implementation", AndroidCore)
			add("implementation", AndroidCoreKtx)
		}

		private fun DependencyHandler.implementMockito() {
			add("implementation", Mockito)
			add("implementation", MockitoKotlin)
		}
	}
}