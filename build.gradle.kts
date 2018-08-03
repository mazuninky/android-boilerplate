plugins {
    base
    kotlin("jvm") version Config.Versions.kotlin apply false
}

buildscript {
    repositories {
        jcenter()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Config.BuildPlugins.androidGradle)
        classpath(kotlin("gradle-plugin", version = Config.Versions.kotlin))
    }
}

subprojects {
    repositories {
        jcenter()
        mavenCentral()
        google()
    }
}
