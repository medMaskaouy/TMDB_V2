package com.maskaouy.moviesviewer.data.database

import androidx.room.*
import com.maskaouy.moviesviewer.data.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM movie")
    fun fetchFavorites() : Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovies(favorite : Movie)

    @Delete
    suspend fun removeFavorite(favorite : Movie)


}