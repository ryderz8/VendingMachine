package com.test.beervm.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.beervm.BR
import com.test.beervm.R
import com.test.beervm.data.DependencyProvider
import com.test.beervm.databinding.ActivityBeerBinding
import com.test.beervm.viewmodels.BeerViewModel
import com.test.core.base.BaseActivity

class BeerActivity : BaseActivity<ActivityBeerBinding, BeerViewModel>() {

    private lateinit var beerListAdapter: BeerListAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_beer
    }

    override fun getBindingVariable(): Int {
        return BR.beerviewmodel
    }

    override fun initializeViews(bundle: Bundle?) {
        getViewDataBinding()?.lifecycleOwner = this
        initRecyclerView()
        getViewModel().getListItem()
    }

    private fun initRecyclerView() {
        beerListAdapter = BeerListAdapter()
        getViewDataBinding()?.recyclerview?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getViewDataBinding()?.recyclerview?.adapter = beerListAdapter
    }

    override fun getViewModel(): BeerViewModel {
        val dependency = DependencyProvider.provideBeerViewModelFactory(this)
        return ViewModelProvider(this, dependency).get(BeerViewModel::class.java)
    }


}