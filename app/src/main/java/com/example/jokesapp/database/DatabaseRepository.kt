package com.example.jokesapp.database

import com.example.jokesapp.model.Jokes


interface DatabaseRepository {
    suspend fun getJokes(): List<Jokes>
}

class DatabaseRepositoryImp(
    private val jokesDatabase: JokesDAO
) : DatabaseRepository {
    override suspend fun getJokes(): List<Jokes> {
        return jokesDatabase.getJokes()

    }
}