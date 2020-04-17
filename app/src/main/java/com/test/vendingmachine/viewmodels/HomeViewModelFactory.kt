package com.test.vendingmachine.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.vendingmachine.data.IHomeRepository

class HomeViewModelFactory(private val iHomeRepository: IHomeRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(iHomeRepository) as T
    }
}