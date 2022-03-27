package com.example.jokesapp.di

import android.content.Context
import androidx.room.Room
import com.example.jokesapp.Network.JokeService
import com.example.jokesapp.database.JokesDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    fun providesLogginInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    fun providesOkHttpCLient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


    fun providesNetworkServices(okHttpClient: OkHttpClient): JokeService = Retrofit.Builder()
        .baseUrl(JokeService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(JokeService::class.java)

    single { providesLogginInterceptor() }
    single { providesNetworkServices(get()) }
    single { providesOkHttpCLient(get()) }
}

val applicationModule = module {
    fun providesJokesDatabase(context: Context): JokesDatabase =
        Room.databaseBuilder(
            context,
            JokesDatabase::class.java,
            "jokes-db"
        ).build()

    single { providesJokesDatabase(androidContext()) }

}