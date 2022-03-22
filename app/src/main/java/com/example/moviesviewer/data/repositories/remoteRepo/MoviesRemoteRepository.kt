package com.maskaouy.moviesviewer.data.repositories.remoteRepo

import com.maskaouy.moviesviewer.data.api.MoviesApi
import com.maskaouy.moviesviewer.data.models.Movie
import com.maskaouy.moviesviewer.data.models.responses.MovieResponse
import com.example.moviesviewer.common.Results
import com.example.moviesviewer.common.handleResponse
import com.example.moviesviewer.common.safeApiCall
import javax.inject.Inject

class MoviesRemoteRepository@Inject constructor(var moviesApi: MoviesApi) {

    suspend fun fetchMoviesByQuery(query : String, lastPage : Int) = safeApiCall{
                    moviesApi.getMoviesByQuery(query = query,
                            page = lastPage)
                            .handleResponse(
                                    onError = {
                                        Results.Error(Exception(it))
                                    },
                                    onSuccess = {
                                        Results.Success(it.results.mapToMovies())
                                    }
                            )
    }
}

fun List<MovieResponse>.mapToMovies() : List<Movie>{
    return filter { it.poster_path != null }
            .map { it.toMovie() }
}
