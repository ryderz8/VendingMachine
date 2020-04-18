package com.test.beervm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.beervm.data.BeerRepository

class BeerViewModelFactory(private val beerRepository: BeerRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeerViewModel(beerRepository) as T
    }
}