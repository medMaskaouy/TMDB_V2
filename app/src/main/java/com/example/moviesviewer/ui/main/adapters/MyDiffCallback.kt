package com.example.moviesviewer.ui.main.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.sgma.prod.adapters.compositeAdapter.DelegateItem


class MyDiffCallback : DiffUtil.ItemCallback<DelegateItem>() {
    override fun areItemsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
        return oldItem.itemId() == newItem.itemId()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: DelegateItem, newItem: DelegateItem): Any? {
        return oldItem.payload(newItem)
    }

}