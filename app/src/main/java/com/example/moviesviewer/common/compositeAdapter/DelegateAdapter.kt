package com.sgma.prod.adapters.compositeAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class DelegateAdapter {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bindViewHolder(model: Any, viewHolder:  RecyclerView.ViewHolder, payloads: List<Any>)
    abstract fun isForViewType(item: Any): Boolean


    open fun onRecycled(holder: RecyclerView.ViewHolder) = Unit
    open fun onAttachedToWindow(holder: RecyclerView.ViewHolder) = Unit
    open fun onDetachedFromWindow(holder: RecyclerView.ViewHolder) = Unit

}