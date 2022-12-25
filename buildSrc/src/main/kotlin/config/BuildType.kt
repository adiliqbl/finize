package config

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

enum class BuildType(val minify: Boolean, val applicationIdSuffix: String? = null) {
	debug(minify = false, applicationIdSuffix = ".debug"),
	release(minify = true)
}

fun Project.configureBuildType(
	commonExtension: CommonExtension<*, *, *, *>
) {
	commonExtension.apply {
		buildTypes {
			BuildType.values().forEach {
				getByName(it.name).run {
					this.isMinifyEnabled = it.minify
					if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
						if (it.applicationIdSuffix != null) {
							this.applicationIdSuffix = it.applicationIdSuffix
						}
					}
					this.proguardFiles(
						getDefaultProguardFile("proguard-android-optimize.txt"),
						"proguard-rules.pro"
					)
				}
			}
		}
	}
}