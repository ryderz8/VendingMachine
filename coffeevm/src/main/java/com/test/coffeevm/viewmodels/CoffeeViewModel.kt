package com.test.coffeevm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.test.coffeevm.data.CoffeeRepository
import com.test.core.base.BaseViewModel
import com.test.repository.entity.CoffeeVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeViewModel(private val coffeeRepository: CoffeeRepository) : BaseViewModel() {
    // Using LiveData and caching what coffeedata returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val listItem: LiveData<List<CoffeeVM>> = coffeeRepository.allItem


    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun updateItem() {
        viewModelScope.launch(Dispatchers.IO) {
            listItem.value?.get(0)?.let {
                it.litre += 1
                coffeeRepository.insertScannedData(it)
            }
        }
    }
}