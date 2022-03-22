package com.example.moviesviewer.ui.main

import androidx.lifecycle.*
import com.example.moviesviewer.common.trigger
import com.example.moviesviewer.ui.common.BaseViewModel
import com.maskaouy.moviesviewer.data.models.Movie
import com.maskaouy.moviesviewer.data.repositories.MoviesRepository
import com.example.moviesviewer.common.Results
import com.example.moviesviewer.common.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(var moviesrepository: MoviesRepository) : BaseViewModel() {

    val searchQueryLiveData = MutableLiveData<String>()
    private var loadedMovies  = mutableListOf<Movie>()
    private var doLoad = true
    private var currentPage = 1

    private val _updatedMoviesList = MutableLiveData<List<Movie>>()
    val updatedMoviesList : LiveData<List<Movie>> get() = _updatedMoviesList

    /**
     * add the movie to favorite table  and norify the composite adapter to refresh the list.
     */
    fun addToFavorite(movie :Movie)   {
       loadedMovies  = loadedMovies.map {
            when(it.id == movie.id){
                true -> it.copy(isFavorite = !movie.isFavorite)
                else  -> it
            }
        }.toMutableList()

        _updatedMoviesList.value = loadedMovies

        viewModelScope.launch(coroutineExceptionHandler) {
            moviesrepository.handleFavorite(movie.copy(isFavorite = !movie.isFavorite))
        }

    }

    /**
     * processes the repository response and manage loading.
     */
    var  moviesList: LiveData<List<Movie>> = searchQueryLiveData.switchMap { SearchQueryString ->
        liveData(coroutineExceptionHandler) {
            showLoading()
            val result = moviesrepository.fetchMoviesByQuery(SearchQueryString,currentPage)
            when(result){
                is Results.Error -> {
                    _serverError.trigger(result.exception)
                    doLoad = false
                    hideLoading()
                }
                is Results.Success -> {
                    loadedMovies.addAll(result.data)
                    if(!result.data.isNullOrEmpty()){
                        doLoad = true
                    }
                     currentPage++
                    emit(loadedMovies.toList())
                    hideLoading()
                }
            }.exhaustive
        }
    }

    /**
     * query listner
     */
    fun moviesSearhStream(queryString: String) {
        loadedMovies  = mutableListOf<Movie>()
        doLoad = true
         currentPage = 1
        searchQueryLiveData.postValue(queryString)
    }

    /**
     * handles the pagination
     */
    fun managePagination() {
        val immutableQuery = searchQueryLiveData.value
        if ((immutableQuery != null && doLoad)) {
                doLoad = false
                searchQueryLiveData.postValue(immutableQuery!!)
        }
    }

    fun init(){
        moviesSearhStream("action")
    }

}