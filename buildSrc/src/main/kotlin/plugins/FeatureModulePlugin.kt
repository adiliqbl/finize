package plugins

import Libraries
import Libraries.UI.implementScreens
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class FeatureModulePlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply {
				apply("finize.module")
				apply("finize.hilt")
				apply("finize.compose")
			}

			extensions.getByType<LibraryExtension>().apply {
				with(defaultConfig) {
					testInstrumentationRunner = "com.adiliqbal.finize.testing.FinizeTestRunner"
				}
			}

			dependencies {
				add("implementation", project(":common"))
				add("implementation", project(":model"))
				add("implementation", project(":data"))
				add("implementation", project(":ui"))
				add("implementation", project(":navigation"))

				implementScreens()
				add("implementation", Libraries.Kotlin.Coroutines)

				add("testImplementation", project(":common:testing"))
				add("androidTestImplementation", project(":common:testing"))
			}
		}
	}
}