package com.example.moviesviewer.common.extensions

import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.example.moviesviewer.R

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)


fun View.handleVisibility(isVisible : Boolean){
    visibility =  if(isVisible) View.VISIBLE else View.INVISIBLE
}

fun View.delayView( body: () -> Unit ) {
    handleVisibility(true)
    Handler().postDelayed({
        body()
    }, 1000)

}

fun View.showView(toshow: Boolean = true) {
    visibility = if (toshow) View.VISIBLE else View.INVISIBLE
}


fun ImageView.loadImage(url: String? = "") {
    Glide.with(context)
        .load("https://image.tmdb.org/t/p/w500/$url")
        .placeholder(R.drawable.ic_image_not_found)
        .into(this)
}


fun ImageView.loadImageDrawable(drawable : Drawable) {
    Glide.with(context)
        .load(drawable)
        .into(this)
}

fun ProgressBar.manageVisibility(toShow : Boolean){
    visibility = if(toShow) View.VISIBLE else View.GONE
}



fun EditText.onTextChangedEvent(block: ()-> Unit){
    this.setOnKeyListener { _, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            block()
            true
        } else {
            false
        }
    }
}


