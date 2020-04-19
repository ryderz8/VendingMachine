package com.test.beervm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.test.beervm.data.BeerRepository
import com.test.core.base.BaseViewModel
import com.test.repository.entity.BeerVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class BeerViewModel(private val beerRepository: BeerRepository) : BaseViewModel() {

    // Using LiveData and caching what beerData returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val listItem: LiveData<List<BeerVM>> = beerRepository.allItem

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun updateItem(price: String, quant: String, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            listItem.value?.get(0)?.let {

                beerRepository.insertScannedData(
                    BeerVM(
                        beer_id = UUID.randomUUID().toString(),
                        vendorId = it.vendorId,
                        beer_brand = type,
                        price = price.toInt(),
                        quantity = quant.toInt()
                    )
                )
            }
        }
    }

}