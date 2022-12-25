package util

import Versions
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlinAndroid(
	commonExtension: CommonExtension<*, *, *, *>,
) {
	commonExtension.apply {
		compileOptions {
			sourceCompatibility = Versions.Build.Java
			targetCompatibility = Versions.Build.Java
			isCoreLibraryDesugaringEnabled = true
		}

		kotlinOptions {
			freeCompilerArgs = freeCompilerArgs + listOf(
				"-opt-in=kotlin.RequiresOptIn",
				// Enable experimental coroutines APIs, including Flow
				"-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
				"-opt-in=kotlinx.coroutines.FlowPreview",
				"-opt-in=kotlin.Experimental",
			)

			jvmTarget = Versions.Build.Java.toString()
		}
	}
}

private fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
	(this as ExtensionAware).extensions.configure("kotlinOptions", block)
}