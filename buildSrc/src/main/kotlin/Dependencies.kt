object Dependencies {

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
        const val lifeCycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.viewModel}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        object Hilt {
            const val android = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
            const val compiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
        }
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val lazyAdapter = "com.serjltt.moshi:moshi-lazy-adapters:2.2"
        const val codgen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Misc{
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val viewBindingDelegate = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBindingDelegate}"
        const val coil = "io.coil-kt:coil:${Versions.coil}"
        const val chuckerDebug = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
        const val chuckerRelease = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
    }

}
