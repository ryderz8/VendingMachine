package com.test.vendingmachine.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.vendingmachine.data.repository.MainActivityRepository

class MainViewModelFactory(private val mainActivityRepository: MainActivityRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainActivityRepository) as T
    }
}