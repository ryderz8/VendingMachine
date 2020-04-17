package com.test.snackvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.snackvm.data.ISnacksRepository

class SnacksViewModelFactory (private val iSnacksRepository: ISnacksRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SnacksViewModel(iSnacksRepository) as T
    }
}