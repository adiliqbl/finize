val KtLintVersion = "0.43.0"

initscript {
	val SpotlessVersion = "6.11.0"

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
				ktlint(KtLintVersion).userData(
					mapOf(
						"android" to "true",
						"max_line_length" to "120",
						"disabled_rules" to "no-wildcard-imports",
						"ktlint_disabled_rules" to "no-wildcard-imports",
						"trim_trailing_whitespace" to "true",
						"ktlint_ignore_back_ticked_identifier" to "true",
					)
				)
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