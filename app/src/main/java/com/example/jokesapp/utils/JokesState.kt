package com.example.jokesapp.utils

import com.example.jokesapp.model.Jokes

sealed class JokesState {
    object LOADING:JokesState()
    class SUCCESS(val jokes:List<Jokes>,  isLocalData:Boolean=false):JokesState()
   // class SUCCESS(val jokes:Jokes):JokesState()
    class ERROR(val error:Throwable):JokesState()

}