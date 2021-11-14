plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlinx-serialization")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.bigoloo.nytimes"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles.addAll(
                listOf<File>(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    File("proguard-rules.pro")
                )
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    //  implementation("androidx.appcompat:appcompat:1.3.1")
    implementation(Deps.materialDesign)
    implementation(Deps.constraintLayout)

    addNetworkDependencies()
    addServiceLocatorDependencies()
    addNavigationDependencies()
    addTestDependencies()

    implementation(project(Modules.Features.home))
    implementation(project(Modules.Features.details))
    implementation(project(Modules.navigation))






    debugImplementation(Deps.leakCanary)
}