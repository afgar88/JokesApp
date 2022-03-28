package com.example.jokesapp

import android.app.Application
import com.example.jokesapp.di.applicationModule
import com.example.jokesapp.di.koinViewModelModule
import com.example.jokesapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JokesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JokesApp)
            modules(listOf(networkModule, applicationModule, koinViewModelModule))
        }
    }
}