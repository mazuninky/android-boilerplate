import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/kotlin")
        }
    }
}

dependencies {
    implementation(project(":core"))

    implementation(Config.Libs.appCompat)
    implementation(Config.Libs.constraintLayout)
    implementation(Config.Libs.cardView)
    implementation(Config.Libs.supportAnnotations)

    implementation(Config.Libs.cicerone)

    implementation(Config.Libs.dagger)
    implementation(Config.Libs.daggerAndroid)
    implementation(Config.Libs.daggerAndroidSupport)
    kapt(Config.Libs.daggerCompiler)
    kapt(Config.Libs.daggerAndroidProcessor)

    implementation(Config.Libs.lifecycle)

    implementation(Config.Libs.kotlin)
    implementation(Config.Libs.kotlinCoroutines)
    implementation(Config.Libs.kotlinCoroutinesAndroid)
}
