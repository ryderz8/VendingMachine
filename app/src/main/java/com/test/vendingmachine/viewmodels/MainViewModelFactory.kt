package com.test.vendingmachine.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.vendingmachine.data.MainActivityRepoContract

class MainViewModelFactory(private val mainActivityRepoContract: MainActivityRepoContract) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainActivityRepoContract) as T
    }
}