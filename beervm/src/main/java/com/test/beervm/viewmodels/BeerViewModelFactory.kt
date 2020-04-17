package com.test.beervm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.beervm.data.IBeerRepository

class BeerViewModelFactory(private val iBeerRepository: IBeerRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeerViewModel(iBeerRepository) as T
    }
}