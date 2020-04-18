package com.test.snackvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.test.core.base.BaseViewModel
import com.test.repository.entity.SnacksVM
import com.test.snackvm.data.SnacksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class SnacksViewModel(private val snacksRepository: SnacksRepository) : BaseViewModel() {

    // Using LiveData and caching what snacksData returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val listItem: LiveData<List<SnacksVM>> = snacksRepository.allItem

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun updateItem(name: String, price: String, quant: String, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            listItem.value?.get(0)?.let {

                snacksRepository.insertScannedData(
                    SnacksVM(
                        id = UUID.randomUUID().toString(),
                        vendorId = it.vendorId,
                        name = name,
                        type = type,
                        price = price.toInt(),
                        quantity = quant.toInt()
                    )
                )
            }
        }
    }

}