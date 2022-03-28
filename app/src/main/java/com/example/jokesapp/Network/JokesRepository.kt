package com.example.jokesapp.Network

import com.example.jokesapp.model.Jokes
import retrofit2.Response


interface JokesRepository {
    suspend fun getAllJokes(): Response<List<Jokes>>
    suspend fun getRandomJoke(): Response<Jokes>
}


class JokesRepositoryImp(
    private val jokeService: JokeService
) : JokesRepository {
    override suspend fun getAllJokes(): Response<List<Jokes>> =
        jokeService.getAllJokes()


    override suspend fun getRandomJoke(): Response<Jokes> =
        jokeService.getRandomJoke()


}