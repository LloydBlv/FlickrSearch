plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "ir.zinutech.android.flickrsearch.data"
    compileSdkVersion(Config.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
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
    implementation(project(":domain"))

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