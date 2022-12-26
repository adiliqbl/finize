package plugins

import modules.configureHilt
import org.gradle.api.Plugin
import org.gradle.api.Project

class HiltPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		with(target) {
			configureHilt()
		}
	}
}