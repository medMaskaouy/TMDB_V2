package com.sgma.prod.adapters.compositeAdapter

import androidx.recyclerview.widget.DiffUtil

class DefaultDiffUtils : DiffUtil.ItemCallback<DelegateItem> (){
    override fun areItemsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
        return false
    }
}