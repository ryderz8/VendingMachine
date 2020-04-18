package com.test.vendingmachine.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.beervm.ui.BeerActivity
import com.test.coffeevm.ui.CoffeeActivity
import com.test.core.base.BaseActivity
import com.test.snackvm.ui.SnacksActivity
import com.test.vendingmachine.R
import com.test.vendingmachine.data.di.DependencyProvider
import com.test.vendingmachine.databinding.ActivityHomeBinding
import com.test.vendingmachine.ui.fragments.ListFragment
import com.test.vendingmachine.ui.fragments.MapViewFragment
import com.test.vendingmachine.utilities.Constants
import com.test.vendingmachine.utilities.FragmentHelper
import com.test.vendingmachine.viewmodels.HomeViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun initializeViews(bundle: Bundle?) {
        setBottomNavigation()
        addListFragment()

        MapViewFragment.onClick = {
            when (it) {
                Constants.SNACKS_VM ->
                    startActivity(Intent(this, SnacksActivity::class.java))
                Constants.BEER_VM ->
                    startActivity(Intent(this, BeerActivity::class.java))
                Constants.COFFEE_VM ->
                    startActivity(Intent(this, CoffeeActivity::class.java))
            }
        }
    }

    override fun getViewModel(): HomeViewModel {
        val viewModelFactory = DependencyProvider.provideHomeViewModelFactory(this)

        return ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

    }

    private fun addListFragment() {
        FragmentHelper.replaceFragmentToActivity(
            supportFragmentManager,
            ListFragment(),
            R.id.container
        )
    }

    private fun setBottomNavigation() {
        getViewDataBinding()?.bottomNavigation?.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.list_item -> {
                    if (getViewDataBinding()?.bottomNavigation?.selectedItemId != menuItem.itemId)
                        addClickedFragment(ListFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.map_item -> {
                    if (getViewDataBinding()?.bottomNavigation?.selectedItemId != menuItem.itemId)
                        addClickedFragment(MapViewFragment())

                    return@setOnNavigationItemSelectedListener true
                }

                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun addClickedFragment(fragment: Fragment) {
        FragmentHelper.replaceFragmentToActivity(
            supportFragmentManager,
            fragment,
            R.id.container
        )
    }


}
