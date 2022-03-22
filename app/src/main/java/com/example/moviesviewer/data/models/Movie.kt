package com.maskaouy.moviesviewer.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sgma.prod.adapters.compositeAdapter.DelegateItem
import com.sgma.prod.adapters.compositeAdapter.DisplayableItem
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class Movie(@PrimaryKey val id : Int,
                 val backdrop_path : String?,
                 // TO CHANGE TO DATE
                 val first_air_date : String?,
                 val name : String?,
                 val original_language : String?,
                 val original_name : String?,
                 val overview : String?,
                 val popularity : Double,
                 val poster_path : String?,
                 val vote_average : Double,
                 val vote_count : Int,
                 var isFavorite : Boolean = false): DelegateItem,Parcelable{

    override fun itemId() = id

    override fun itemContent() = isFavorite

    override fun payload(other: Any): DelegateItem.Payloadable {
        val newMovie = other as Movie
        return ChangePayload.SelectedAsFavorite(newMovie.isFavorite)
    }

    sealed class ChangePayload: DelegateItem.Payloadable {
        data class SelectedAsFavorite(val isFavorite: Boolean): ChangePayload()
    }
}
