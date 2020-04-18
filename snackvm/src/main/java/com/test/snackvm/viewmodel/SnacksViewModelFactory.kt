package com.test.snackvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.snackvm.data.SnacksRepository

class SnacksViewModelFactory (private val snacksRepository: SnacksRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SnacksViewModel(snacksRepository) as T
    }
}