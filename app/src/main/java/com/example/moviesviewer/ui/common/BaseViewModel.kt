package com.example.moviesviewer.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesviewer.common.Event
import com.example.moviesviewer.common.trigger
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel :  ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _serverError.trigger(exception)
    }

    private val _loading = MutableLiveData<Event<Boolean>>()
    val loading: LiveData<Event<Boolean>>
        get() = _loading

    protected val _serverError = MutableLiveData<Event<Throwable>>()
    val serverError : LiveData<Event<Throwable>> get() = _serverError

    fun showLoading() =  _loading.trigger(true)
    fun hideLoading() = _loading.trigger(false)
}