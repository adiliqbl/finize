package plugins

import AppConfig
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import util.configureKotlinAndroid

class ModulePlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply {
				apply("com.android.library")
				apply("org.jetbrains.kotlin.android")
			}

			extensions.getByType<LibraryExtension>().apply {
				compileSdk = AppConfig.targetSdk

				configureKotlinAndroid(this)

				defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
			}
		}
	}
}