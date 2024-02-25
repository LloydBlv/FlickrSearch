import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
    }
    dependencies {
        classpath("com.github.ben-manes:gradle-versions-plugin:0.51.0")
        classpath(GradlePlugins.android)
        classpath(GradlePlugins.kotlin)
        classpath(GradlePlugins.hilt)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
    apply(plugin = "com.github.ben-manes.versions")
//    apply(com.github.benmanes.gradle.versions.VersionsPlugin())
    fun isNonStable(version: String): Boolean {
        val stableKeyword =
            listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
    tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
        rejectVersionIf {
            isNonStable(candidate.version)
        }
        // optional parameters
        checkForGradleUpdate = true
        outputFormatter = "html"
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}