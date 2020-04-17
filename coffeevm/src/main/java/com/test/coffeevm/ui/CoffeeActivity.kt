package com.test.coffeevm.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.coffeevm.BR
import com.test.coffeevm.R
import com.test.coffeevm.data.DependencyProvider
import com.test.coffeevm.databinding.ActivityCoffeeBinding
import com.test.coffeevm.viewmodels.CoffeeViewModel
import com.test.core.base.BaseActivity

class CoffeeActivity : BaseActivity<ActivityCoffeeBinding, CoffeeViewModel>() {

    private lateinit var coffeeItemAdapter: CoffeeItemAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_coffee
    }

    override fun getBindingVariable(): Int {
        return BR.coffeeviewmodel
    }

    override fun initializeViews(bundle: Bundle?) {
        getViewDataBinding()?.lifecycleOwner = this
        initRecyclerView()
        getViewModel().getListItem()

        getViewDataBinding()?.fab?.setOnClickListener {
           getViewModel().updateItem()
        }
    }

    private fun initRecyclerView(){
        coffeeItemAdapter = CoffeeItemAdapter()
        getViewDataBinding()?.recyclerview?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getViewDataBinding()?.recyclerview?.adapter = coffeeItemAdapter
    }

    override fun getViewModel(): CoffeeViewModel {
        val dependency = DependencyProvider.provideCoffeeViewModelFactory(this)
        return ViewModelProvider(this, dependency).get(CoffeeViewModel::class.java)
    }

}