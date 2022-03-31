package com.example.jokesapp.model


import com.google.gson.annotations.SerializedName

data class JokesList(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: List<Value>
)