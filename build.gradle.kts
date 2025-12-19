// TOP-LEVEL build.gradle.kts (Giraffe and newer)

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.2")
        classpath("com.google.gms:google-services:4.4.0")
    }
}

plugins {
    // DO NOT add com.android.application here.
    // Leave this block empty or with version catalogs if you use them.
}
