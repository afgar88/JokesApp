package com.example.jokesapp.model


import com.google.gson.annotations.SerializedName

data class ValueX(
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("joke")
    val joke: String
)