package com.example.jokesapp.database

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun stringToList(stringList: String): List<String> {
        return stringList.split(",")
    }

    @TypeConverter
    fun listToString(listString: List<String>): String {
        return listString.joinToString { "," }
    }
}