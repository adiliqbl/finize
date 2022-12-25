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
rootProject.name = "finize"
include(":app")
include(":data")

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