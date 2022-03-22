package com.maskaouy.moviesviewer.data.models

import com.google.gson.annotations.SerializedName
import com.maskaouy.moviesviewer.data.models.responses.MovieResponse

data class MoviesResults(val page : Int,
                         val results : List<MovieResponse>,
                         @SerializedName("total_pages")
                         val totalPages : Int,
                         val total_results : Int)
