val KtLintVersion = "0.43.0"
val SpotlessVersion = "6.11.0"

initscript {
	repositories {
		mavenCentral()
	}

	dependencies {
		classpath("com.diffplug.spotless:spotless-plugin-gradle:$SpotlessVersion")
	}
}

rootProject {
	subprojects {
		apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
		extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
			kotlin {
				target("**/*.kt")
				targetExclude("**/build/**/*.kt")
				ktlint(KtLintVersion).userData(mapOf("android" to "true"))
			}
			format("kts") {
				target("**/*.kts")
				targetExclude("**/build/**/*.kts")
			}
			format("xml") {
				target("**/*.xml")
				targetExclude("**/build/**/*.xml")
			}
		}
	}
}