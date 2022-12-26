buildscript {
	dependencies {
		classpath(Dependencies.Build.GoogleService)
		classpath(Dependencies.Hilt.GradlePlugin)
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