package plugins

import com.android.build.api.dsl.ApplicationExtension
import config.configureBuildType
import config.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import util.configureKotlinAndroid

class ApplicationPlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply {
				apply("com.android.application")
				apply("org.jetbrains.kotlin.android")
			}

			extensions.configure<ApplicationExtension> {
				configureKotlinAndroid(this)
				configureBuildType(this)
				configureFlavors(this)
			}
		}
	}
}