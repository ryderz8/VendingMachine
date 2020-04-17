package com.test.coffeevm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.coffeevm.data.ICoffeeRepository

class CoffeeViewModelFactory(private val iCoffeeRepository: ICoffeeRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoffeeViewModel(iCoffeeRepository) as T
    }
}