package com.example.jokesapp.model


import com.google.gson.annotations.SerializedName

data class Jokes(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: Value
)