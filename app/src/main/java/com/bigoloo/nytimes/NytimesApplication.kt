package com.bigoloo.nytimes

import android.app.Application
import com.bigoloo.nytimes.detail.di.detailModule
import com.bigoloo.nytimes.di.networkModule
import com.bigoloo.nytimes.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NytimesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeServiceLocator()
    }

    private fun initializeServiceLocator() {

        startKoin {
            androidContext(this@NytimesApplication)
            modules(
                listOf(
                    networkModule,
                    homeModule,
                    detailModule
                )
            )

        }
    }
}