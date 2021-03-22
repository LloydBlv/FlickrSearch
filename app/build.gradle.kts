plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    buildToolsVersion(Config.buildToolsVersion)

    defaultConfig {
        applicationId = "ir.zinutech.android.flickrsearch"
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = Config.androidTestInstrumentation

        buildConfigField("String", "FLICKR_API_KEY", "\"5a2cc90782760b3a6b3eca570dfaf5c3\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":data"))

    /*Kotlin*/
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Kotlin.coroutinesCore)

    /*AndroidX*/
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.material)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.viewModelKtx)
    implementation(Dependencies.AndroidX.lifeCycleCommon)

    /*Misc*/
    implementation(Dependencies.Misc.timber)
    implementation(Dependencies.Misc.viewBindingDelegate)
    implementation(Dependencies.Misc.coil)

    /*Dagger*/
    implementation(Dependencies.Dagger.Hilt.android)
    implementation(Dependencies.Dagger.Hilt.viewModel)
    kapt(Dependencies.Dagger.Hilt.compiler)
}