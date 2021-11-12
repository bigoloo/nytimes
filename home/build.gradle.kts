plugins {
    id("com.android.library")
    id ("kotlin-android")
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

    implementation( "androidx.core:core-ktx:1.7.0")
    implementation( "androidx.appcompat:appcompat:1.3.1")
    implementation( "com.google.android.material:material:1.4.0")
    implementation( "androidx.constraintlayout:constraintlayout:2.1.1")
    implementation( "io.insert-koin:koin-core:$koin_version")
    testImplementation( "io.insert-koin:koin-test:$koin_version")
    implementation( "io.insert-koin:koin-android:$koin_version")
    implementation( "io.insert-koin:koin-androidx-navigation:$koin_version")
    implementation( "io.insert-koin:koin-androidx-compose:$koin_version")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    testImplementation( "junit:junit:4.+")
    androidTestImplementation( "androidx.test.ext:junit:1.1.3")
    androidTestImplementation( "androidx.test.espresso:espresso-core:3.4.0")

    testImplementation("io.mockk:mockk:1.12.0")

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
}