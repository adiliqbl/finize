package plugins

import com.android.build.api.dsl.LibraryExtension
import modules.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class ModulePlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply {
				apply("com.android.library")
				apply("org.jetbrains.kotlin.android")
				apply("kotlin-kapt")
			}

			extensions.getByType<LibraryExtension>().apply {
				configureKotlinAndroid(this)

				defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
			}
		}
	}
}