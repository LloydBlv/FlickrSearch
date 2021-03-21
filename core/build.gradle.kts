plugins {
    id("kotlin")
    kotlin("kapt")
}


dependencies {
    /*Kotlin*/
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Kotlin.coroutinesCore)

    /*Dagger*/
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.compiler)
}