plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    buildToolsVersion(Config.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName
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
    implementation(project(":domain"))
    implementation(project(":core"))

    /*Dagger*/
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.Hilt.android)
    kapt(Dependencies.Dagger.Hilt.compiler)

    /*Retrofit*/
    api(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.moshiConverter)

    /*Moshi*/
    api(Dependencies.Moshi.moshi)
    implementation(Dependencies.Moshi.lazyAdapter)
    kapt(Dependencies.Moshi.codgen)

    /*Chucker*/
    debugImplementation(Dependencies.Misc.chuckerDebug)
    releaseImplementation(Dependencies.Misc.chuckerRelease)
}