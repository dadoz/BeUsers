plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
// Apply the App Distribution Gradle plugin
    id("com.google.firebase.appdistribution")
    id("com.google.gms.google-services")
//apply from: "versions.gradle"
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.davidelmn.application.frenzspots"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        dataBinding = true
    }

    ndkVersion = "22.0.7026061"

    buildTypes {
        getByName("debug") {
            buildConfigField("String","REQUEST_ID_TOKEN", "\"353159220680-9tdqq1jetpmjn4og4hbh8e6u51cqmr4p.apps.googleusercontent.com\"")
            firebaseAppDistribution {
                releaseNotes = "this is commit"
                testers = "davide.lamanna.appsquad@gmail.com"
                serviceCredentialsFile = "frenzspots-firebase-adminsdk-sb2yl-0656d11144.json"
            }
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

//    task buildAndDistribute {
//        println "assemble and upload debug"
//        app.assembleDebug
//        app.appDistributionUploadDebug
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("com.google.firebase:firebase-analytics:19.0.0")

    implementation("com.google.android.gms:play-services-maps:17.0.1")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.google.maps.android:android-maps-utils-v3:1.3.1")

//    // If you are using Places, add all of the dependencies below
//    implementation name:"places-maps-sdk-3.1.0-beta", ext:"aar"
    implementation("com.google.android.gms:play-services-gcm:17.0.0")
    implementation("com.google.auto.value:auto-value-annotations:1.6.5")

    implementation("com.google.firebase:firebase-database-ktx:20.0.0")
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:25.12.0"))

    // Declare the dependency for the Firebase Authentication library
    implementation("com.google.firebase:firebase-core:19.0.0")
    // When using the BoM, you don"t specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth-ktx:21.0.1")
// Also declare the dependency for the Google Play services library and specify its version
    implementation("com.google.android.gms:play-services-auth:19.0.0")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    implementation("com.google.android.material:material:1.4.0")

    implementation("com.jakewharton.timber:timber:4.7.1")
    implementation("com.android.support:support-annotations:28.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

}