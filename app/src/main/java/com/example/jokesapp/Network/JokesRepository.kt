package com.example.jokesapp.Network

import com.example.jokesapp.model.Jokes
import com.example.jokesapp.model.JokesList
import retrofit2.Response


interface JokesRepository {
   suspend fun getAllJokes(exclude:String): Response<JokesList>
    suspend fun getRandomJoke(): Response<Jokes>
    suspend fun getCustomJoke(firstName: String, lastName: String): Response<Jokes>
}


class JokesRepositoryImp(
    private val jokeService: JokeService
) : JokesRepository {
    override suspend fun getAllJokes(exclude:String): Response<JokesList> =
        jokeService.getAllJokes(exclude)

    override suspend fun getRandomJoke(): Response<Jokes> =
        jokeService.getRandomJoke()

    override suspend fun getCustomJoke(firstName: String, lastName: String): Response<Jokes> =
        jokeService.getCustomJoke(firstName, lastName)


}