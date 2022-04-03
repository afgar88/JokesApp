package com.example.jokesapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.Network.JokesRepository
import com.example.jokesapp.database.DatabaseRepository
import com.example.jokesapp.model.Jokes
import com.example.jokesapp.model.JokesList
import com.example.jokesapp.model.Value
import com.example.jokesapp.utils.JokesState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class JokesViewModel(
    private val networkRepo: JokesRepository,
    private val databaseRepo: DatabaseRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

   var explicit = ""

    val _jokes: MutableLiveData<JokesState> = MutableLiveData(JokesState.LOADING)

    val allJokes: LiveData<JokesState> get() = _jokes

    fun getJokes(exclude:String) {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = networkRepo.getAllJokes(exclude)
                if (response.isSuccessful) {
                    response.body()?.let {
                        databaseRepo.insertJokes(it.value)
                        val localData = databaseRepo.getAllJokes()
                        Log.d("DataBase",localData.toString())
                        _jokes.postValue(JokesState.SUCCESS(localData))
                    } ?: throw Exception("Response in null")
                } else {
                    throw Exception("No successful response")
                }
            } catch (e: Exception) {
                _jokes.postValue(JokesState.ERROR(e))
                     loadFromDB()
            }
        }
    }

    private suspend fun loadFromDB() {
        try {
            val localData = databaseRepo.getAllJokes()
                _jokes.postValue(JokesState.SUCCESS(localData, isLocalData = true))
        } catch (e: Exception) {
            _jokes.postValue(JokesState.ERROR(e))
        }
    }


    fun getRandomJoke() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = networkRepo.getRandomJoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("Broma1", it.toString())
                        val jok: Value = it.value
                        _jokes.postValue(JokesState.SUCCESS(jok))

                        Log.d("Broma", _jokes.toString())
                    } ?: throw Exception("Response in null")
                } else {
                    throw Exception("No successful response")
                }
            } catch (e: Exception) {
                _jokes.postValue(JokesState.ERROR(e))
            }
        }
    }

    fun getCustomJoke(firstName: String, lastName: String) {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = networkRepo.getCustomJoke(firstName, lastName)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("Broma1", it.toString())
                        val jok: Value = it.value
                        _jokes.postValue(JokesState.SUCCESS(jok))

                        Log.d("Broma", _jokes.toString())
                    } ?: throw Exception("Response in null")
                } else {
                    throw Exception("No successful response")
                }
            } catch (e: Exception) {
                _jokes.postValue(JokesState.ERROR(e))
            }
        }
    }
}
