object Versions {

    val navigation = "2.3.5"
    val retrofit = "2.9.0"
    val retrofitSerialization = "0.8.0"
    val kotlinSerialization = "1.3.1"
    val koin = "3.1.3"
    val leakCanary = "2.7"
    val material = "1.4.0"
    val constraintLayout = "2.1.1"
    val okhttpLogger = "4.9.2"
    val junit = "4.+"
    val androidJunit = "1.1.3"
    val espressoCore = "3.4.0"
    val androidXCore = "1.7.0"
    val lifecycleRuntimeKTX = "2.4.0"
    val coil = "1.4.0"
    val mockK = "1.12.0"
    val coroutineTest = "1.5.2"
}





object Modules {
    object Features {
        val home = ":home"
        val details = ":detail"
    }

    val navigation = ":navigation"
}

object Deps {

    val coil = "io.coil-kt:coil:${Versions.coil}"
    val lifecycleRuntimeKTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKTX}"
    val androidXCore = "androidx.core:core-ktx:${Versions.androidXCore}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitSerialization =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitSerialization}"

    val kotlinSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
    val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogger}"


    object Test {
        val junit = "junit:junit:${Versions.junit}"
        val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTest}"
        val mockK = "io.mockk:mockk:${Versions.mockK}"
    }

    object Navigation {
        val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    }

    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val materialDesign = "com.google.android.material:material:${Versions.material}"

    object Koin {
        val core = "io.insert-koin:koin-core:${Versions.koin}"
        val android = "io.insert-koin:koin-android:${Versions.koin}"
        val navigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
    }

    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
}