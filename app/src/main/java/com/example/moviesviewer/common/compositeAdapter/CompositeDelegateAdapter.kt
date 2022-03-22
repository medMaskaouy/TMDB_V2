package com.sgma.prod.adapters.compositeAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CompositeDelegateAdapter(private val delegates: List<DelegateAdapter>,
                               differCallback: DiffUtil.ItemCallback<DelegateItem> = DefaultDiffUtils()) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemViewType(position: Int): Int = delegates.indexOfFirst { it.isForViewType(differ.currentList[position]) }
            .takeIf { it != -1 }
            ?: error("There is no adapter for type ${differ.currentList[position].javaClass} at position: $position")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegates[viewType].createViewHolder(parent)



    override fun getItemCount() = differ.currentList.size

    fun submitList(list : List<DelegateItem>) {
        differ.submitList(list)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = onBindViewHolder(holder, position, mutableListOf())

    override fun onBindViewHolder(
            holder: RecyclerView.ViewHolder,
            position: Int,
            payloads: MutableList<Any>
    ) {
        val delegateAdapter = delegates[getItemViewType(position)]
        val delegatePayloads = payloads.map { it as DelegateItem.Payloadable }
        delegateAdapter.bindViewHolder(differ.currentList[position], holder, delegatePayloads)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onRecycled(holder)
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onDetachedFromWindow(holder)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        delegates[holder.itemViewType].onAttachedToWindow(holder)
        super.onViewAttachedToWindow(holder)
    }

}