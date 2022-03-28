package com.example.jokesapp.database

import com.example.jokesapp.model.Jokes


interface DatabaseRepository {
    suspend fun insertJokes(jokes: List<Jokes>)
    suspend fun getAllJokes(): List<Jokes>
    suspend fun getRandomJokes(): Jokes
}

class DatabaseRepositoryImp(
    private val jokesDatabase: JokesDAO
) : DatabaseRepository {
    override suspend fun insertJokes(jokes: List<Jokes>) {
        jokesDatabase.insertJokes(jokes)
    }

    override suspend fun getAllJokes(): List<Jokes>{
        return jokesDatabase.getJokes()

    }

    override suspend fun getRandomJokes(): Jokes{
        return jokesDatabase.getRandomJoke()
    }
}