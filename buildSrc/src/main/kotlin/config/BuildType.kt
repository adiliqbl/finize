package config

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import java.io.File

enum class BuildType(val minify: Boolean, val applicationIdSuffix: String? = null) {
	debug(minify = false, applicationIdSuffix = ".debug"), release(minify = true)
}

fun Project.configureBuildType(
	commonExtension: CommonExtension<*, *, *, *>
) {
	commonExtension.apply {
		buildTypes {
			BuildType.values().forEach { build ->
				getByName(build.name).run {
					this.isMinifyEnabled = build.minify
					if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
						if (build.applicationIdSuffix != null) {
							this.applicationIdSuffix = build.applicationIdSuffix
						}

						if (build == BuildType.release) {
							this.signingConfig = signingConfigs.getByName("release").apply {
								keyAlias = System.getProperty("finize.key")
								keyPassword = System.getProperty("finize.keyPass")
								storePassword = System.getProperty("finize.storePass")
								storeFile = File("keystore.jks")
							}
						}
					}

					if (build == BuildType.release) {
						this.proguardFiles(
							getDefaultProguardFile("proguard-android-optimize.txt"),
							"proguard-rules.pro"
						)
					}
				}
			}
		}
	}
}

fun CommonExtension<*, *, *, *>.configureBuildTypes() {
	buildTypes {
		BuildType.values().forEach { build ->
			getByName(build.name).run {
				this.isMinifyEnabled = build.minify
			}
		}
	}
}