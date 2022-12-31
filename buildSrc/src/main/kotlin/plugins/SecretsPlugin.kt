package plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class SecretsPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

			extensions.getByType<LibraryExtension>().apply {
				buildFeatures {
					buildConfig = true
				}
			}
		}
	}
}