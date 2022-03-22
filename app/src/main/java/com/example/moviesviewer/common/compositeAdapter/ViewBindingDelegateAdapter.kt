package com.sgma.prod.adapters.compositeAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ViewBindingDelegateAdapter<T : Any, V : ViewBinding>(
        private val viewBindingInflater: (LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> V
) : DelegateAdapter () {

    final override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = viewBindingInflater.invoke(layoutInflater, parent, false)
        val viewholder = ViewBindingHolder(
                viewBinding
        )
        viewBinding.onViewHolderCreated(viewholder)
        return viewholder
    }

    override fun bindViewHolder(
            model: Any,
            viewHolder: RecyclerView.ViewHolder,
            payloads: List<Any>
    ) {
        viewHolder as ViewBindingHolder<V>
        viewHolder.viewBinding.onBind(model as T,payloads)
    }

    abstract fun V.onBind(item: T,payloads: List<Any>)
    open fun V.onViewHolderCreated(viewHolder: RecyclerView.ViewHolder) {}

    private class ViewBindingHolder<V : ViewBinding>(
            val viewBinding: V
    ) : RecyclerView.ViewHolder(viewBinding.root)
}