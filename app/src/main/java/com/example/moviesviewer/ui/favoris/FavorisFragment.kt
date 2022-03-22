package com.maskaouy.moviesviewer.ui.favoris

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesviewer.common.ToastManager
import com.example.moviesviewer.databinding.FragmentMoviesFavorisBinding
import com.example.moviesviewer.ui.common.BaseFragment
import com.maskaouy.moviesviewer.data.models.Movie
import com.example.moviesviewer.common.Results
import com.example.moviesviewer.common.exhaustive
import com.example.moviesviewer.common.observe
import com.maskaouy.moviesviewer.ui.favoris.adapters.FavoriteAdapter
import com.sgma.prod.adapters.compositeAdapter.CompositeDelegateAdapter
import com.sgma.prod.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavorisFragment : BaseFragment() {

    private val viewModel: FavoriteViewModel by viewModels()

     private val favoriteAdapter by lazy {
        CompositeDelegateAdapter(listOf(
                FavoriteAdapter { favoriteListner (it) }
        ))
    }

    private val binding by viewBinding (FragmentMoviesFavorisBinding::bind)

    fun favoriteListner(movie: Movie){
        viewModel.removeFromFavorite(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMoviesFavorisBinding.inflate(inflater,container,false).root

    override fun initAdapters() {
        super.initAdapters()
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.favoritesRecyclerView.layoutManager = layoutManager
        binding.favoritesRecyclerView.adapter = favoriteAdapter

        observe(viewModel.favoriteList){
            when(it){
                is Results.Success -> {
                    favoriteAdapter.submitList( it.data)
                }
                is Results.Error -> {
                    ToastManager.showToast(requireContext(),it.exception.message!!)
                }
            }.exhaustive

            showLoading(false)
        }

        observe(viewModel.loading){
            showLoading(it.peekContent())
        }
    }

}