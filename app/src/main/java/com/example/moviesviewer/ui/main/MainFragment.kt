package com.example.moviesviewer.ui.main

import  MoviesAdapter
import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesviewer.R
import androidx.navigation.fragment.findNavController
import com.example.moviesviewer.common.ToastManager
import com.example.moviesviewer.common.extensions.onScrolledVertically
import com.example.moviesviewer.common.extensions.onTextChangedEvent
import com.example.moviesviewer.databinding.MainFragmentBinding
import com.example.moviesviewer.ui.common.BaseFragment
import com.example.moviesviewer.ui.main.adapters.MyDiffCallback
import com.maskaouy.moviesviewer.data.models.Movie
import com.example.moviesviewer.common.observe
import com.sgma.prod.adapters.compositeAdapter.CompositeDelegateAdapter
import com.sgma.prod.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private val viewModel: MainViewModel by viewModels ()
    val binding  by viewBinding (MainFragmentBinding::bind)

    private val compositeAdapter by lazy {
        CompositeDelegateAdapter(listOf(
                MoviesAdapter{handleMovieClick(it)}
        ), MyDiffCallback())
    }

    override fun onCreateView(
            inflater: LayoutInflater
            ,container: ViewGroup?
            ,savedInstanceState: Bundle?) = MainFragmentBinding.inflate(inflater,container,false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        observe(viewModel.moviesList){
            compositeAdapter.submitList(it)
        }
        observe(viewModel.serverError){
            ToastManager.showToast(requireContext())
        }
        observe(viewModel.updatedMoviesList){
            compositeAdapter.submitList(it)
        }

        observe(viewModel.loading){
            showLoading(it.peekContent())
        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    private fun handleMovieClick(movie: Movie) {
        viewModel.addToFavorite(movie)
    }

    override fun initAdapters() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        compositeAdapter.submitList(mutableListOf())
        binding.moviesRecyclerView.setLayoutManager(layoutManager)
        binding.moviesRecyclerView.adapter = compositeAdapter
    }

    override fun initListeners() {
        super.initListeners()

        binding.searchMovie.onTextChangedEvent{
            val str = binding.searchMovie.text.trim().toString()
            if (str.isNotEmpty()) {
                viewModel.moviesSearhStream(str)
            }
        }

        binding.moviesRecyclerView.onScrolledVertically{
            viewModel.managePagination()
        }

    }

    override fun bindInputs() {
        super.bindInputs()
        viewModel.init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favoris ->{
                findNavController().navigate(R.id.list_to_favorite)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}