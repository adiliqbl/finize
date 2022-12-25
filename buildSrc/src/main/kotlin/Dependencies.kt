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
		const val Hilt = "com.google.dagger:hilt-android:${Versions.Hilt.Hilt}"
		const val GradlePlugin =
			"com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.Hilt}"
		const val Compiler = "com.google.dagger:hilt-compiler:${Versions.Hilt.Hilt}"
		const val Navigation = "androidx.hilt:hilt-navigation-compose:${Versions.Hilt.Navigation}"
		const val WorkManager = "androidx.hilt:hilt-work:${Versions.Hilt.WorkManager}"
		const val WorkManagerCompiler = "androidx.hilt:hilt-compiler:${Versions.Hilt.WorkManager}"
	}

	object Compose {
		const val Compose = "androidx.compose.ui:ui:${Versions.Compose.Compose}"
		const val Foundation = "androidx.compose.foundation:foundation:${Versions.Compose.Compose}"
		const val ViewModel =
			"androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.UI.Lifecycle}"
		const val Lifecycle =
			"androidx.lifecycle:lifecycle-runtime-compose:${Versions.UI.Lifecycle}"
		const val Activity = "androidx.activity:activity-compose:${Versions.Compose.Activity}"
		const val Compiler = "1.3.1"
		const val Paging = "androidx.paging:paging-compose:${Versions.Compose.Paging}"
		const val Preview = "androidx.compose.ui:ui-tooling-preview${Versions.Compose.Compose}"
		const val PreviewTool = "androidx.compose.ui:ui-tooling:${Versions.Compose.Compose}"
		const val TestManifest =
			"androidx.compose.ui:ui-test-manifest:${Versions.Compose.Compose}"
		const val TestJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.Compose}"
	}

	object Navigation {
		const val Navigation =
			"androidx.navigation:navigation-compose:${Versions.Navigation.Navigation}"
		const val Animation =
			"com.google.accompanist:accompanist-navigation-animation:${Versions.Navigation.Animation}"
	}

	object UI {
		const val Material3 = "androidx.compose.material3:material3:${Versions.UI.Material3}"
		const val Icons =
			"androidx.compose.material:material-icons-extended:${Versions.Compose.Compose}"
		const val Splashscreen = "androidx.core:core-splashscreen:${Versions.UI.Splashscreen}"
		const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.UI.Lifecycle}"
		const val Coil = "io.coil-kt:coil-compose:${Versions.UI.Coil}"
	}

	object Room {
		const val Room = "androidx.room:room-ktx:${Versions.Data.Room}"
		const val Runtime = "androidx.room:room-runtime:${Versions.Data.Room}"
		const val Compiler = "androidx.room:room-compiler:${Versions.Data.Room}"
		const val Paging = "androidx.room:room-paging:${Versions.Data.Room}"
		const val Testing = "androidx.room:room-testing:${Versions.Data.Room}"
	}

	object Data {
		const val Datastore = "androidx.datastore:datastore-preferences:${Versions.Data.Datastore}"
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
		const val JUnit = "junit:junit:${Versions.Test.JUnit}"
		const val AndroidJUnit = "androidx.test.ext:junit:${Versions.Test.AndroidJUnit}"
		const val AndroidCore = "androidx.test:core:${Versions.Test.AndroidCore}"
		const val AndroidCoreKtx = "androidx.test:core-ktx:${Versions.Test.AndroidCore}"
		const val CoroutineTest =
			"org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.CoroutineTest}"
		const val Espresso = "androidx.test.espresso:espresso-core:${Versions.Test.Espresso}"
		const val Mockito = "org.mockito:mockito-core:${Versions.Test.Mockito}"
		const val MockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.Test.MockitoKotlin}"
	}
}