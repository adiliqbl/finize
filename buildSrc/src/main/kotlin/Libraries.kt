import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.configurationcache.extensions.capitalized

object Libraries {

	object Build {
		const val GoogleService = "com.google.gms:google-services:${Versions.Build.GoogleService}"
		const val Secrets =
			"com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Versions.Build.Secrets}"
	}

	object Kotlin {
		const val Core = "androidx.core:core-ktx:${Versions.Kotlin.Core}"
		const val Coroutines =
			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.Coroutines}"
		const val DateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.Kotlin.DateTime}"
		const val JsonGradlePlugin =
			"org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.Kotlin}"
		const val Json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.Json}"
		const val JsonConvertor =
			"com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.Kotlin.JsonConvertor}"
	}

	object Google {
		const val PlayServices =
			"com.google.android.gms:play-service:${Versions.Google.PlayServices}"
	}

	object Firebase {
		private const val BOM = "com.google.firebase:firebase-bom:${Versions.Firebase.BOM}"
		const val Crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
		const val CrashlyticsGradlePlugin =
			"com.google.firebase:firebase-crashlytics-gradle:${Versions.Firebase.Crashlytics}"
		const val Analytics = "com.google.firebase:firebase-analytics-ktx"
		const val Auth = "com.google.firebase:firebase-auth-ktx"
		const val Firestore = "com.google.firebase:firebase-firestore-ktx"

		fun DependencyHandler.implementFirebase(type: String = "implementation") {
			add(type, platform(BOM))
		}
	}

	object Hilt {
		private const val Hilt = "com.google.dagger:hilt-android:${Versions.Hilt.Hilt}"
		const val GradlePlugin =
			"com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.Hilt}"
		private const val Compiler = "com.google.dagger:hilt-compiler:${Versions.Hilt.Hilt}"
		internal const val Navigation =
			"androidx.hilt:hilt-navigation-compose:${Versions.Hilt.Navigation}"
		internal const val WorkManager = "androidx.hilt:hilt-work:${Versions.Hilt.WorkManager}"
		internal const val WorkManagerCompiler =
			"androidx.hilt:hilt-compiler:${Versions.Hilt.WorkManager}"

		private const val Test = "com.google.dagger:hilt-android-testing:${Versions.Hilt.Hilt}"
		private const val TestCompiler =
			"com.google.dagger:hilt-android-compiler:${Versions.Hilt.Hilt}"

		fun DependencyHandler.implementHilt() {
			add("implementation", Hilt)
			add("kapt", Compiler)
		}

		fun DependencyHandler.implementHiltTesting() {
			add("androidTestImplementation", Test)
			add("kaptAndroidTest", TestCompiler)
			add("androidTestAnnotationProcessor", TestCompiler)
		}
	}

	object Compose {
		private const val BOM = "androidx.compose:compose-bom:${Versions.Compose.BOM}"
		private const val Compose = "androidx.compose.ui:ui"
		private const val Foundation = "androidx.compose.foundation:foundation"
		private const val Preview = "androidx.compose.ui:ui-tooling"
		private const val PreviewTool = "androidx.compose.ui:ui-tooling-preview"
		private const val ViewModel =
			"androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.UI.Lifecycle}"
		private const val Lifecycle =
			"androidx.lifecycle:lifecycle-runtime-compose:${Versions.UI.Lifecycle}"
		private const val Activity =
			"androidx.activity:activity-compose:${Versions.Compose.Activity}"

		private const val TestManifest = "androidx.compose.ui:ui-test-manifest"
		private const val TestJUnit = "androidx.compose.ui:ui-test-junit4"

		fun DependencyHandler.implementCompose(type: String = "implementation") {
			add(type, platform(BOM))
			add(type, Compose)
			add(type, Foundation)
			add("debug${type.capitalized()}", PreviewTool)
			add(type, Preview)
			add(type, Activity)
			add(type, ViewModel)
			add(type, Lifecycle)
		}

		fun DependencyHandler.implementComposeTesting() {
			add("debugImplementation", TestManifest)
			add("androidTestImplementation", TestJUnit)
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
		private const val Icons = "androidx.compose.material:material-icons-extended"
		const val Splashscreen = "androidx.core:core-splashscreen:${Versions.UI.Splashscreen}"
		const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.UI.Lifecycle}"
		const val Paging = "androidx.paging:paging-compose:${Versions.Compose.Paging}"
		const val Coil = "io.coil-kt:coil-compose:${Versions.UI.Coil}"

		fun DependencyHandler.implementMaterialDesign(type: String = "implementation") {
			add(type, Material3)
			add(type, Icons)
		}
	}

	object Room {
		const val Room = "androidx.room:room-ktx:${Versions.Data.Room}"
		const val Runtime = "androidx.room:room-runtime:${Versions.Data.Room}"
		const val Compiler = "androidx.room:room-compiler:${Versions.Data.Room}"
		const val Paging = "androidx.room:room-paging:${Versions.Data.Room}"
		const val Testing = "androidx.room:room-testing:${Versions.Data.Room}"
	}

	object Data {
		const val Datastore =
			"androidx.datastore:datastore-preferences:${Versions.Data.Datastore}"
	}

	object WorkManager {
		private const val WorkManager =
			"androidx.work:work-runtime-ktx:${Versions.Data.WorkManager}"
		private const val Testing = "androidx.work:work-testing:${Versions.Data.WorkManager}"

		fun DependencyHandler.implementWorkManager(type: String = "implementation") {
			add(type, WorkManager)
			add("androidTest${type.capitalized()}", Testing)
			add(type, Hilt.WorkManager)
			add(type, Hilt.WorkManagerCompiler)
		}
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
		private const val AndroidRunner = "androidx.test:runner:${Versions.Test.AndroidRunner}"
		private const val AndroidJUnit = "androidx.test.ext:junit:${Versions.Test.AndroidJUnit}"
		private const val AndroidJUnitKtx =
			"androidx.test.ext:junit-ktx:${Versions.Test.AndroidJUnit}"
		private const val AndroidCore = "androidx.test:core:${Versions.Test.AndroidCore}"
		private const val AndroidCoreKtx = "androidx.test:core-ktx:${Versions.Test.AndroidCore}"
		private const val CoroutineTest =
			"org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.CoroutineTest}"
		private const val Mockito = "org.mockito:mockito-core:${Versions.Test.Mockito}"
		private const val MockitoKotlin =
			"org.mockito.kotlin:mockito-kotlin:${Versions.Test.MockitoKotlin}"

		fun DependencyHandler.implementTesting() {
			add("testImplementation", JUnit)
			add("testImplementation", CoroutineTest)
			add("testImplementation", Mockito)
			add("testImplementation", MockitoKotlin)
		}

		fun DependencyHandler.implementAndroidTesting() {
			add("androidTestImplementation", CoroutineTest)
			add("androidTestImplementation", AndroidRunner)
			add("androidTestImplementation", AndroidJUnit)
			add("androidTestImplementation", AndroidJUnitKtx)
			add("androidTestImplementation", AndroidCore)
			add("androidTestImplementation", AndroidCoreKtx)
		}
	}
}