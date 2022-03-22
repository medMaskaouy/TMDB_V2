package com.maskaouy.moviesviewer.data.models.responses
import android.os.Parcelable
import com.maskaouy.moviesviewer.data.models.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
 data class MovieResponse( val id : Int,
                 val backdrop_path : String?,
                 val first_air_date : String?,
                 val name : String?,
                 val original_language : String?,
                 val original_name : String?,
                 val overview : String?,
                 val popularity : Double,
                 val poster_path : String?,
                 val vote_average : Double,
                 val vote_count : Int,
                 var isFavorite : Boolean = false): Parcelable{
                 fun toMovie() = Movie (
                         id ,
                         backdrop_path,
                         first_air_date ,
                         name ,
                         original_language,
                         original_name,
                         overview,
                         popularity,
                         poster_path ,
                         vote_average,
                         vote_count,
                         isFavorite)
}
