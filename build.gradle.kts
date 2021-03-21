buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
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
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}