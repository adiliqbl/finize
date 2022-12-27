import org.gradle.api.JavaVersion

object Versions {
	object Build {
		val Java = JavaVersion.VERSION_1_8
		const val Desugar = "1.1.5"
		const val GoogleService = "4.3.13"
		const val Secrets = "2.0.1"
	}

	object Kotlin {
		const val Kotlin = "1.7.20"
		const val Core = "1.9.0"
		const val Coroutines = "1.6.2"
		const val DateTime = "0.4.0"
		const val Json = "1.3.3"
		const val JsonConvertor = "0.8.0"
	}

	object Firebase {
		const val BOM = "31.1.0"
		const val Crashlytics = "2.9.1"
	}

	object Hilt {
		const val Hilt = "2.44.2"
		const val Navigation = "1.0.0"
		const val WorkManager = "1.0.0"
	}

	object Compose {
		const val BOM = "2022.12.00"
		const val Compiler = "1.3.2"
		const val Activity = "1.6.1"
		const val Paging = "1.0.0-alpha17"
	}

	object Navigation {
		const val Navigation = "2.5.1"
		const val Animation = "0.26.2-beta"
	}

	object UI {
		const val Material3 = "1.0.1"
		const val Splashscreen = "1.0.0"
		const val Lifecycle = "2.6.0-alpha01"
		const val Coil = "2.2.2"
	}

	object Data {
		const val Room = "2.4.3"
		const val Datastore = "1.0.0"
		const val WorkManager = "2.7.1"
	}

	object Network {
		const val Retrofit = "2.9.0"
		const val OkHttp = "4.9.3"
	}

	object Util {
		const val Timber = "5.0.1"
		const val Inject = "1"
	}

	object Test {
		const val JUnit = "4.13.2"
		const val AndroidJUnit = "1.1.3"
		const val AndroidCore = "1.4.0"
		const val CoroutineTest = "1.6.3"
		const val Mockito = "4.5.1"
		const val MockitoKotlin = "4.0.0"
	}
}