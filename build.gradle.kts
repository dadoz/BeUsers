// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath(kotlin("gradle-plugin", version = "1.5.20"))
        classpath("com.google.gms:google-services:4.3.8")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.ktx files
        // Add the App Distribution Gradle plugin
        classpath("com.google.firebase:firebase-appdistribution-gradle:2.1.3")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}