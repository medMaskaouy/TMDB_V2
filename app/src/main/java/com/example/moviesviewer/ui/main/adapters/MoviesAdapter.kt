import androidx.recyclerview.widget.RecyclerView
import com.example.moviesviewer.R
import com.example.moviesviewer.common.*
import com.example.moviesviewer.common.extensions.delayView
import com.example.moviesviewer.common.extensions.handleVisibility
import com.example.moviesviewer.common.extensions.loadImage
import com.example.moviesviewer.common.extensions.loadImageDrawable
import com.example.moviesviewer.databinding.ItemMovieBinding
import com.maskaouy.moviesviewer.data.models.Movie
import com.sgma.prod.adapters.compositeAdapter.ViewBindingDelegateAdapter
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesAdapter(val listener: ((Movie) -> Unit))
        : ViewBindingDelegateAdapter<Movie, ItemMovieBinding>(ItemMovieBinding::inflate)  {

    override fun ItemMovieBinding.onBind(item: Movie, payloads: List<Any>) = with(item){

        var isFavorite = item.isFavorite
        if(payloads.size>0){

          payloads.handlePayLoads<Movie.ChangePayload>{
                when(it){
                    is  Movie.ChangePayload.SelectedAsFavorite ->{
                        isFavorite  = item.isFavorite
                    }
                }
            }


            if(isFavorite){
                GlobalScope.launch {
                    withContext(Dispatchers.Main){
                        root.animationView.delayView{
                            animationView.handleVisibility(false)
                        }
                    }
                }
            }
        }else{
            tvMovieImage.loadImage(item.poster_path)
            tvRate.text = item.vote_average.toString()
            tvPopularity.text = item.popularity.toString()
            tvMovieTitle.text = item.name
        }

        val res = if(isFavorite)  R.drawable.ic_favorite_selected else  R.drawable.ic_favorite
        tvFavorite.loadImageDrawable(root.context.resources.getDrawable(res,null) )
        root.tag = item
    }

    override fun ItemMovieBinding.onViewHolderCreated(viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.setOnClickListener {
            listener(root.tag as Movie)
        }
    }


    override fun isForViewType(item: Any) = item is Movie


}
