package com.example.moviesviewer.common

import android.content.Context
import android.widget.Toast
import com.example.moviesviewer.R
import com.example.moviesviewer.common.extensions.getResource


object ToastManager {

        private var currentToast: Toast? = null

        fun showToast(ctx: Context, text: String = "") {
            try {
                dismissToast()
                val msg = if(text.isNullOrEmpty()) R.string.error_search_with_new_query.getResource(ctx) else text
                currentToast = Toast.makeText(ctx, msg, Toast.LENGTH_LONG)
                currentToast!!.show()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        fun dismissToast() {
            currentToast?.let {
                it.cancel()
            }
        }

}