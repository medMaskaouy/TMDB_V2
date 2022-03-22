package com.maskaouy.moviesviewer.data.repositories.localRepo

import com.maskaouy.moviesviewer.data.database.FavoriteDao
import com.maskaouy.moviesviewer.data.database.MoviesDao
import com.maskaouy.moviesviewer.data.models.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesLocalRepositorie @Inject constructor(private val moviesDao: MoviesDao,
                                                 private val favoriteDao: FavoriteDao)
{
    fun fetchAllMovies() = moviesDao.fetchMovies()

    suspend  fun insertMovies ( movies : List<Movie>) = moviesDao.insertMovies(movies)

    suspend fun resetTable() = moviesDao.resetTable()

    suspend fun sortByDate() = moviesDao.sortByDate()

    suspend fun sortAlphabetically() = moviesDao.sortAlphabetically()

    suspend fun addToFavorite(favorite : Movie) = favoriteDao.insertFavoriteMovies(favorite)

    suspend fun removeFavorite(favorite : Movie) = favoriteDao.removeFavorite(favorite)

    fun fetchFavorites() = favoriteDao.fetchFavorites()

}