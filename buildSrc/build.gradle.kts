plugins {
	`kotlin-dsl`
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
	google()
	mavenCentral()
	gradlePluginPortal()
}

dependencies {
	implementation("com.android.tools.build:gradle:7.3.1")
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.1")
}

gradlePlugin {
	plugins {
		register("application") {
			id = "finize.application"
			implementationClass = "plugins.ApplicationPlugin"
		}
		register("module") {
			id = "finize.module"
			implementationClass = "plugins.ModulePlugin"
		}
		register("feature") {
			id = "finize.feature"
			implementationClass = "plugins.FeatureModulePlugin"
		}
		register("application-compose") {
			id = "finize.application-compose"
			implementationClass = "plugins.ComposeApplicationPlugin"
		}
		register("secrets") {
			id = "finize.secrets"
			implementationClass = "plugins.SecretsPlugin"
		}

		register("compose") {
			id = "plugins.compose"
			implementationClass = "plugins.ComposeModulePlugin"
		}
		register("hilt") {
			id = "plugins.hilt"
			implementationClass = "plugins.HiltPlugin"
		}

		register("serialization") {
			id = "plugins.serialization"
			implementationClass = "plugins.SerializationPlugin"
		}
	}
}