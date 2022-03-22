package com.example.moviesviewer.common.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onScrolledVertically(block: ()-> Unit){
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (! recyclerView.canScrollVertically(1)) {
                block()
            }

        }
    })
}


fun Int.getResource(context : Context) = context.getString(this)

@SuppressLint("UseCompatLoadingForDrawables")
fun RecyclerView.ViewHolder.getDrawable(id : Int, theme : Resources.Theme?=null) = itemView.context.resources.getDrawable( id,theme)
