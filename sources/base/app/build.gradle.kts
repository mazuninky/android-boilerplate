plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName
        multiDexEnabled = true
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    dexOptions {
        preDexLibraries = false
        dexInProcess = false
        javaMaxHeapSize = "4g"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            consumerProguardFiles("proguard-rules.pro")
        }
        getByName("debug") {
            var enableCrashlytics by extra { false }
            lintOptions{
                disable("MissingTranslation")
            }
            ndk {
                //abiFilter("armeabi-v7a")
                //abiFilter("x86")
            }
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
    implementation(project(":coreui"))
    implementation(project(":repository"))
    implementation(project(":domain"))

    implementation(project(":launcher"))
    implementation(project(":mainscreen"))

    implementation(Config.Libs.cicerone)

    testImplementation(Config.TestLibs.jUnit)
    androidTestImplementation(Config.TestLibs.testRunner)
    androidTestImplementation(Config.TestLibs.espressoCore)

    implementation(Config.Libs.appCompat)
    implementation(Config.Libs.constraintLayout)
    implementation(Config.Libs.design)
    implementation(Config.Libs.cardView)

    implementation(Config.Libs.dagger)
    implementation(Config.Libs.daggerAndroid)
    implementation(Config.Libs.daggerAndroidSupport)
    kapt(Config.Libs.daggerCompiler)
    kapt(Config.Libs.daggerAndroidProcessor)

    implementation(Config.Libs.kotlin)
    implementation(Config.Libs.kotlinCoroutines)
}
