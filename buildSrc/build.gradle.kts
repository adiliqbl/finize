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
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.1")
}

gradlePlugin {
	plugins {
		register("application") {
			id = "finize-application"
			implementationClass = "plugins.ApplicationPlugin"
		}
		register("module") {
			id = "finize-module"
			implementationClass = "plugins.ModulePlugin"
		}
		register("application-compose") {
			id = "finize-application-compose"
			implementationClass = "plugins.ComposeApplicationPlugin"
		}
		register("compose") {
			id = "finize-compose"
			implementationClass = "plugins.ComposeModulePlugin"
		}
		register("hilt") {
			id = "finize-hilt"
			implementationClass = "plugins.HiltPlugin"
		}
	}
}