buildscript {
	dependencies {
		classpath(Libraries.Build.GoogleService)
		classpath(Libraries.Build.Secrets)
		classpath(Libraries.Hilt.GradlePlugin)
		classpath(Libraries.Kotlin.JsonGradlePlugin)
		classpath(Libraries.Firebase.CrashlyticsGradlePlugin)
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.Kotlin}")
	}

	repositories {
		google()
		mavenCentral()
		maven { url = uri("https://jitpack.io") }
	}
}

tasks.register("clean", Delete::class) {
	delete(rootProject.buildDir)
}

/**
 * Spotless Tasks
 *
 * ./gradlew --init-script scripts/spotless.gradle.kts --no-configuration-cache check
 * ./gradlew --init-script scripts/spotless.gradle.kts --no-configuration-cache spotlessApply
 */
tasks.register("validate", Exec::class) {
	commandLine = listOf(
		"./gradlew",
		"--init-script",
		"gradlew",
		"scripts/spotless.gradle.kts",
		"--no-configuration-cache",
		"check"
	)
}
tasks.register("lint", Exec::class) {
	commandLine = listOf(
		"./gradlew",
		"--init-script",
		"gradlew",
		"scripts/spotless.gradle.kts",
		"--no-configuration-cache",
		"spotlessApply"
	)
}