package com.example.jokesapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.jokesapp.database.Converter
import com.google.gson.annotations.SerializedName
@Entity
@TypeConverters(Converter::class)
data class Value(
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("joke")
    val joke: String
)