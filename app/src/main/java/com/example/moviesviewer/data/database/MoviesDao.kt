package com.maskaouy.moviesviewer.data.database

import androidx.room.*
import com.maskaouy.moviesviewer.data.models.Movie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie")
    fun fetchMovies() : List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Update
    suspend fun update(movie: Movie)

    @Query("DELETE FROM movie")
    suspend fun resetTable()

    @Query("SELECT * FROM movie ORDER BY name ASC")
    suspend fun sortAlphabetically(): List<Movie>

    @Query("SELECT * FROM movie ORDER BY first_air_date DESC")
    suspend fun sortByDate(): List<Movie>
}