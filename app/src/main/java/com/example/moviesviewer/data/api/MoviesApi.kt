package com.maskaouy.moviesviewer.data.api

import com.example.moviesviewer.BuildConfig
import com.maskaouy.moviesviewer.data.models.MoviesResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    companion object {
        const val API_BASE_URL = BuildConfig.TMDB_BASE_URL
    }

    @GET("3/search/tv?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US&include_adult=false")
    suspend fun getMoviesByQuery(@Query("page") page: Int, @Query("query") query: String): Response<MoviesResults>

}