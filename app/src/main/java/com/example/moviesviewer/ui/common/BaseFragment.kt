package com.example.moviesviewer.ui.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moviesviewer.common.extensions.showView

open class BaseFragment  : Fragment(){

    val loadingDialog  by ProgressBarDelegate(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initAdapters()
        initListeners()
        bindInputs()
    }

    open fun observeViewModel() = Unit

    open fun initAdapters() = Unit

    open fun initListeners() = Unit

    open fun bindInputs() = Unit


    protected fun showLoading(toshow : Boolean= true) = loadingDialog.showView(toshow)
}