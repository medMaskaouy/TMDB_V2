package com.maskaouy.moviesviewer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maskaouy.moviesviewer.data.models.Movie

@Database(
    entities = [Movie::class],
    version = 5,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val DATABASE_NAME = "moviesdatab.02"
    }


}