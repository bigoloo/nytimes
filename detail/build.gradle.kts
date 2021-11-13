plugins {
    id("com.android.library")
    id ("kotlin-android")
    id("kotlinx-serialization")
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk= 21
        targetSdk= 31
        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles.addAll(listOf<File>(getDefaultProguardFile("proguard-android-optimize.txt"),File( "proguard-rules.pro")))
        }
    }
    compileOptions {
        sourceCompatibility= JavaVersion.VERSION_1_8
        targetCompatibility= JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}
val koin_version = "3.1.3"

dependencies {

    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation( "androidx.core:core-ktx:1.7.0")
    implementation( "androidx.appcompat:appcompat:1.3.1")
    implementation( "com.google.android.material:material:1.4.0")
    implementation( "androidx.constraintlayout:constraintlayout:2.1.1")
    testImplementation( "junit:junit:4.+")
    implementation( "io.insert-koin:koin-core:$koin_version")
    testImplementation( "io.insert-koin:koin-test:$koin_version")
    implementation( "io.insert-koin:koin-android:$koin_version")
    implementation( "io.insert-koin:koin-androidx-navigation:$koin_version")
    //implementation( "io.insert-koin:koin-androidx-compose:$koin_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    androidTestImplementation( "androidx.test.ext:junit:1.1.3")
    androidTestImplementation( "androidx.test.espresso:espresso-core:3.4.0")
    implementation(project(":navigation"))
}