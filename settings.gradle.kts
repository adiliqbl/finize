pluginManagement {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
	}
	resolutionStrategy {
		eachPlugin {
			if (requested.id.namespace == "com.android") {
				useModule("com.android.tools.build:gradle:${requested.version}")
			}
		}
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

val prePushHook = file(".git/hooks/pre-push")
val commitMsgHook = file(".git/hooks/commit-msg")
val hooksInstalled = commitMsgHook.exists()
	&& prePushHook.exists()
	&& prePushHook.readBytes().contentEquals(file("scripts/pre-push").readBytes())

if (!hooksInstalled) {
	exec {
		commandLine("scripts/setup.sh")
		workingDir = rootProject.projectDir
	}
}

rootProject.name = "finize"
include(":app")
include(":data")
include(":network")
include(":database")
include(":datastore")
include(":ui")
include(":sync")
include(":navigation")
include(":model")
include(":common")
include(":common:testing")
include(":feature:home")
include(":feature:accounts")
include(":feature:budgets")
include(":feature:analytics")
include(":feature:transactions")
include(":feature:templates")
include(":feature:auth")
