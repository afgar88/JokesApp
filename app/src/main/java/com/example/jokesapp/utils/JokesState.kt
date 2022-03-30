package com.example.jokesapp.utils

import com.example.jokesapp.model.Jokes

sealed class JokesState {
    object LOADING:JokesState()
    class SUCCESS<T>(val joke: T,  isLocalData:Boolean=false):JokesState()
    class ERROR(val error:Throwable):JokesState()


}