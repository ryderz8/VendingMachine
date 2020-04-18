package com.test.coffeevm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.coffeevm.data.CoffeeRepository

class CoffeeViewModelFactory(private val coffeeRepository: CoffeeRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoffeeViewModel(coffeeRepository) as T
    }
}