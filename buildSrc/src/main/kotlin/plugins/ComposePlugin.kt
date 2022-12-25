package plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import util.configureAndroidCompose

class ComposeApplicationPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply("com.android.application")
			val extension = extensions.getByType<ApplicationExtension>()
			configureAndroidCompose(extension)
		}
	}
}

class ComposeModulePlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply("com.android.library")
			val extension = extensions.getByType<LibraryExtension>()
			configureAndroidCompose(extension)
		}
	}
}