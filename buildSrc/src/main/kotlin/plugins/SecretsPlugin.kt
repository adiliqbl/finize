package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class SecretsPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
		}
	}
}