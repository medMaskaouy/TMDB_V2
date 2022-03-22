package com.maskaouy.moviesviewer.ui.favoris

import androidx.lifecycle.*
import com.example.moviesviewer.ui.common.BaseViewModel
import com.maskaouy.moviesviewer.data.models.Movie
import com.maskaouy.moviesviewer.data.repositories.MoviesRepository
import com.example.moviesviewer.common.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(val moviesrepository: MoviesRepository) : BaseViewModel(){

    private val _favoriteList = moviesrepository.fetchFavorites().asLiveData()
    val favoriteList : LiveData<Results<List<Movie>>>
        get() = _favoriteList

    fun removeFromFavorite(movie : Movie){
        showLoading()
        viewModelScope.launch(coroutineExceptionHandler) {
            delay(500) // this delay is optional, it's used to give more time for the loading progress to show
            moviesrepository.handleFavorite( movie.apply {
                isFavorite = false
            })
        }
    }
}