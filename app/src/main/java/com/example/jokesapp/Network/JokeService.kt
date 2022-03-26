package com.example.jokesapp.Network

import com.example.jokesapp.model.Jokes
import retrofit2.Response
import retrofit2.http.GET

interface JokeService {

    @GET(JOKES_PATH)
    suspend fun getAllJokes(): Response<List<Jokes>>

    @GET(RANDOM_JOKES_PATH)
    suspend fun getRandomJoke(): Response<Jokes>


    companion object {
        const val BASE_URL = "https://api.icndb.com/"
        private const val JOKES_PATH = "jokes"

        private const val RANDOM_JOKES_PATH = "jokes/random"

    }
}