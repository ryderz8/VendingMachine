package com.test.vendingmachine.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.beervm.ui.BeerActivity
import com.test.coffeevm.ui.CoffeeActivity
import com.test.core.base.BaseFragment
import com.test.snackvm.ui.SnacksActivity
import com.test.vendingmachine.BR
import com.test.vendingmachine.R
import com.test.vendingmachine.data.di.DependencyProvider
import com.test.vendingmachine.databinding.FragmentListBinding
import com.test.vendingmachine.ui.adapters.ListItemAdapter
import com.test.vendingmachine.utilities.Constants
import com.test.vendingmachine.viewmodels.HomeViewModel

class ListFragment : BaseFragment<FragmentListBinding, HomeViewModel>() {

    private lateinit var listItemAdapter: ListItemAdapter

    override fun initializeController() {
    }

    override fun initializeLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun initializeViews(savedInstanceState: Bundle?) {
        getViewDataBinding()?.lifecycleOwner = this
        setupToolbar()
        setUpRecyclerView()
        getViewModel().getItem()
    }

    override fun getViewModel(): HomeViewModel {
        val viewModelFactory = DependencyProvider.provideHomeViewModelFactory(activity!!)

        return ViewModelProvider(activity!!, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun getBindingVariable(): Int {
        return BR.viewmodel
    }

    private fun setUpRecyclerView() {
        listItemAdapter = ListItemAdapter()
        getViewDataBinding()?.recyclerView?.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        getViewDataBinding()?.recyclerView?.adapter = listItemAdapter

        listItemAdapter.onItemClick = {
            when (it.name) {
                Constants.SNACKS_VM ->
                    startActivity(Intent(activity!!, SnacksActivity::class.java))
                Constants.BEER_VM ->
                    startActivity(Intent(activity!!, BeerActivity::class.java))
                Constants.COFFEE_VM ->
                    startActivity(Intent(activity!!, CoffeeActivity::class.java))

            }

        }
    }

    private fun setupToolbar() {
        displayHomeAsUpEnabled(false)
    }


}