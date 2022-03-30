package com.example.jokesapp.Network

import com.example.jokesapp.model.Jokes
import com.example.jokesapp.model.JokesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeService {

    @GET(JOKES_PATH)
    suspend fun getAllJokes(
        @Query("exclude") exclude:String
    ): Response<JokesList>

    @GET(LIST_JOKES_PATH)
    suspend fun getRandomJoke(): Response<Jokes>

    @GET(LIST_JOKES_PATH)
    suspend fun getCustomJoke(
        @Query("firstName") firstName: String,
        @Query("lastName") lastName: String
    ): Response<Jokes>

    companion object {
        const val BASE_URL = "https://api.icndb.com/"
        private const val JOKES_PATH = "jokes"

        private const val LIST_JOKES_PATH = "jokes/random"

    }
}