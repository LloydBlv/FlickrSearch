plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ir.zinutech.android.flickrsearch"

    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        applicationId = "ir.zinutech.android.flickrsearch"
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = "ir.zinutech.android.flickrsearch.CustomTestRunner"

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
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
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
    kapt(Dependencies.Dagger.Hilt.compiler)

    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test:runner:1.5.2")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")

    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.50")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.50")

    debugImplementation("androidx.fragment:fragment-testing:1.6.2")

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.willowtreeapps.assertk:assertk:0.28.0")
    testImplementation("io.mockk:mockk-android:1.13.8")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

}