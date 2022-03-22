package com.maskaouy.moviesviewer.data.repositories

import com.maskaouy.moviesviewer.data.models.Movie
import com.maskaouy.moviesviewer.data.repositories.localRepo.MoviesLocalRepositorie
import com.maskaouy.moviesviewer.data.repositories.remoteRepo.MoviesRemoteRepository
import com.example.moviesviewer.common.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

private const val MOVIES_STARTING_PAGE_INDEX = 1
private const val MOVIES_PAGE_ITEMS = 20


@Singleton
class MoviesRepository @Inject
            constructor(private  val remoteRepository: MoviesRemoteRepository,
                        private  val localRepositorie: MoviesLocalRepositorie){

    suspend fun fetchMoviesByQuery(query: String, page : Int) = remoteRepository.fetchMoviesByQuery(query,page)


    fun fetchFavorites() : Flow<Results<List<Movie>>> = flow {
        localRepositorie.fetchFavorites()
            .collect {
                if (it.isNullOrEmpty()){
                    emit(Results.Error(Exception("No favorite items")))
                }else{
                    emit(Results.Success(it))
                }
            }
    }.flowOn(Dispatchers.IO)

    suspend fun handleFavorite(movie: Movie){
        if(movie.isFavorite){
            localRepositorie.addToFavorite(movie)
        }else{
            localRepositorie.removeFavorite(movie)
        }
    }

    /**
     *
     * improve this code to work with the offline mode-database
     *
        suspend  fun getLastPageFromDB(movies : List<Movie>){
            lastRequestedPage = movies.size/MOVIES_PAGE_ITEMS
            if (movies.size%MOVIES_PAGE_ITEMS == 0) ++lastRequestedPage else lastRequestedPage+=2
            searchResults.emit(Results.Success(movies))
        }

        suspend fun requestDataFromNetWork(query: String){
            val result = remoteRepository.fetchMoviesByQuery(query,lastRequestedPage)
            when(result){
                is Results.Error -> {
                    //isRequestInProgress = false
                    searchResults.emit(Results.Error(result.exception))
                }
                is Results.Success -> {
                    lastRequestedPage++
                    // isRequestInProgress = false
                    localRepositorie.insertMovies(result.data)
                    withContext(Dispatchers.IO){
                        val movies = localRepositorie.fetchAllMovies()
                        searchResults.emit(Results.Success(movies))
                    }
                }
            }.exhaustive

        }

        suspend fun sortMovies(sortType: SortType){
            withContext(Dispatchers.IO){
                var movies  =  emptyList<Movie>()
                when(sortType){
                    SortType.ALPHABETICALLY ->{
                        movies = localRepositorie.sortAlphabetically()
                    }
                    SortType.BYDATE ->{
                        movies = localRepositorie.sortByDate()
                    }
                }
                if(!movies.isEmpty())
                    searchResults.emit(Results.Success(movies))
            }
        }

        suspend fun handleFavorite(movie: Movie){
            if(movie.isFavorite){
                localRepositorie.addToFavorite(movie.toFavorite())
            }else{
                localRepositorie.removeFavorite(movie.toFavorite())
            }
        }

     */

}