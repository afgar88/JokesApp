package com.example.jokesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.Network.JokesRepository
import com.example.jokesapp.database.DatabaseRepository
import com.example.jokesapp.model.Jokes
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

    val _jokes: MutableLiveData<JokesState> = MutableLiveData(JokesState.LOADING)
    val allJokes: LiveData<JokesState> get() = _jokes

    fun getJokes() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val response = networkRepo.getAllJokes()
                if (response.isSuccessful) {
                    response.body()?.let {
                        databaseRepo.insertJokes(it)
                        val localData = databaseRepo.getAllJokes()
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
                        databaseRepo.insertJokes(arrayListOf(it))
                        val localData = databaseRepo.getRandomJokes()
                        _jokes.postValue(JokesState.SUCCESS(arrayListOf(localData)))
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
