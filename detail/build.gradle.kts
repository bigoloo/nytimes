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

dependencies {


    implementation(Deps.androidXCore)
    implementation(Deps.materialDesign)
    implementation(Deps.constraintLayout)
    implementation(Deps.lifecycleRuntimeKTX)

    addNetworkDependencies()
    addServiceLocatorDependencies()

    implementation(project(Modules.navigation))
}