plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.igorj.core"
}

dependencies {
    implementation(Security.securityCrypto)
    implementation(Volley.volley)
    implementation(Gson.gson)
}