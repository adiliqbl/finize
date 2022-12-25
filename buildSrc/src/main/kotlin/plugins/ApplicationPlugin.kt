package plugins

import AppConfig
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
				defaultConfig.apply {
					applicationId = AppConfig.appId
					targetSdk = AppConfig.targetSdk

					versionCode = AppConfig.versionCode
					versionName = AppConfig.versionName
				}

				configureKotlinAndroid(this)
				configureBuildType(this)
				configureFlavors(this)
			}
		}
	}
}