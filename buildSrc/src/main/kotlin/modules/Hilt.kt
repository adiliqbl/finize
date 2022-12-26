package modules

import Dependencies.Hilt.implementHilt
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHilt() {
	pluginManager.apply("dagger.hilt.android.plugin")

	dependencies {
		implementHilt()
	}
}