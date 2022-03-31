package com.example.jokesapp.database

import androidx.room.*
import com.example.jokesapp.model.JokesList
import com.example.jokesapp.model.Value

@Database(
    entities = [Value::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class JokesDatabase : RoomDatabase() {
    abstract fun getJokesDao(): JokesDAO
}

@Dao
interface JokesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJokes(newJokes: List<Value>)

    @Query("SELECT* FROM value")
    suspend fun getJokes(): List<Value>

    @Query("SELECT * FROM value WHERE id=:searchId")
    suspend fun getJokesById(searchId: Int): Value

    @Query("SELECT * FROM value")
    suspend fun getRandomJoke(): Value

    @Delete
    suspend fun deleteAllJokes(jokes: List<Value>)


}