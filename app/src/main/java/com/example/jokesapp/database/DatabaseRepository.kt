package com.example.jokesapp.database

import com.example.jokesapp.model.JokesList
import com.example.jokesapp.model.Value


interface DatabaseRepository {
    suspend fun insertJokes(jokes: List<Value>)
    //suspend fun getAllJokes(): List<JokesList>
    suspend fun getRandomJokes(): Value

}

class DatabaseRepositoryImp(
    private val jokesDatabase: JokesDAO
) : DatabaseRepository {
    override suspend fun insertJokes(jokes: List<Value>) {
        jokesDatabase.insertJokes(jokes)
    }

//    override suspend fun getAllJokes(): JokesList {
//        return jokesDatabase.getJokes()
//
//    }

    override suspend fun getRandomJokes(): Value {
        return jokesDatabase.getRandomJoke()
    }
}