package com.example.moviesviewer.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import retrofit2.Response


fun <P,V> Response<P>.handleResponse(
    onSuccess : (data : P) -> Results<V>,
    onError : (error : String) -> Results.Error
) : Results<V> {
    return if(isSuccessful){
        if(body() != null){
            onSuccess(body()!!)
        }else{
            onError("body is empty")
        }
    }else{
        var error = ""
        when(code()){
            403 ->  error = "Access to resource is forbidden"
            404 ->  error = "Resource not found"
            500 ->  error = "Internal server error"
            502 ->  error = "Bad Gateway"
            301 ->  error = "Resource has been removed permanently"
            302 ->  error = "Resource moved, but has been found"
            401 ->  error = "Unauthorized Acces"
        }
        onError(error)
    }
}

inline fun <T> safeApiCall(  call : () -> Results<T>) : Results<T> {
    return try {
        call()
    }catch (e : Exception){
        Results.Error(e)
    }
}

internal fun <T> LifecycleOwner.observe(liveData: LiveData<T>, body: (T) -> Unit = {}) {
    val owner = if (this is Fragment) viewLifecycleOwner else this
    liveData.observe(owner, { it?.let { t -> body(t) } })
}


inline fun <reified T> List<Any>.handlePayLoads(expression : (p : T) ->Unit){
    for(payload in this){
        expression(payload as T)
    }
}


