package com.example.jokesapp.database

import androidx.room.*
import com.example.jokesapp.model.Jokes

@Database(
    entities = [Jokes::class],
    version = 1
)
abstract class JokesDatabase : RoomDatabase() {
    abstract fun getJokesDao(): JokesDAO
}

@Dao
interface JokesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJokes(newJokes: List<Jokes>)

    @Query("SELECT* FROM jokes")
    suspend fun getJokes(): List<Jokes>

    @Query("SELECT * FROM jokes WHERE id=:searchId")
    suspend fun getJokesById(searchId: Int): Jokes

    @Query("SELECT * FROM jokes ORDER BY RAND() LIMIT 1;")
    suspend fun getRandomJoke(): Jokes

    @Delete
    suspend fun deleteAllJokes(jokes: List<Jokes>)


}