package modules

import Libraries.Hilt.implementHilt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHilt() {
	pluginManager.apply("dagger.hilt.android.plugin")

	dependencies {
		implementHilt()
	}
}