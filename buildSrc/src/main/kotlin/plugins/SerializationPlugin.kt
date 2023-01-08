package plugins

import Libraries
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SerializationPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply("kotlinx-serialization")

			dependencies {
				add("implementation", Libraries.Kotlin.Json)
			}
		}
	}
}