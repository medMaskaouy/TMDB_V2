package com.maskaouy.moviesviewer.ui.favoris.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.moviesviewer.R
import com.example.moviesviewer.common.extensions.loadImage
import com.example.moviesviewer.common.extensions.loadImageDrawable
import com.example.moviesviewer.databinding.ItemMovieBinding
import com.maskaouy.moviesviewer.data.models.Movie
import com.sgma.prod.adapters.compositeAdapter.ViewBindingDelegateAdapter


class FavoriteAdapter(val listener: ((Movie) -> Unit)): ViewBindingDelegateAdapter<Movie, ItemMovieBinding>( ItemMovieBinding::inflate)   {

    override fun ItemMovieBinding.onBind(item: Movie, payloads: List<Any>) =  with(item) {
        tvMovieImage.loadImage(item.poster_path)
        tvRate.text = vote_average.toString()
        tvPopularity.text = popularity.toString()
        tvMovieTitle.text = name
        tvFavorite.loadImageDrawable(root.context.resources.getDrawable(R.drawable.ic_favorite_selected,null) )
        root.tag = item
     }

    override fun ItemMovieBinding.onViewHolderCreated(viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.setOnClickListener {
            listener(root.tag as Movie)
        }
    }

    override fun isForViewType(items: Any) =   items is Movie
}