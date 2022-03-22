package com.example.moviesviewer.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object SharedPreferencesManager {
    lateinit var preferences: SharedPreferences

    private const val PREFERENCES_FILE_NAME = "TMDB"

    fun with(application: Context) {
        preferences = application.getSharedPreferences(
            PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun <T> put(`object`: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        preferences.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> get(key: String): T? {
        val value = preferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}