package com.test.vendingmachine.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.core.base.BaseViewModel
import com.test.repository.model.ItemVMBeer
import com.test.repository.model.ItemVMCoffee
import com.test.repository.model.ItemVMSnacks
import com.test.repository.entity.VendingMachineEntity
import com.test.vendingmachine.data.repository.HomeRepository
import com.test.vendingmachine.utilities.Constants
import com.test.vendingmachine.utilities.Transformer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {

    var mListItem: MutableLiveData<List<Any>> = MutableLiveData()

    var snackData = emptyList<ItemVMSnacks>()
    var beerData = emptyList<ItemVMBeer>()
    var coffeeData = emptyList<ItemVMCoffee>()

    fun getItem() {
        if (mListItem.value?.size ?: -1 > 0) {
            mListItem.postValue( null)
        }
        viewModelScope.launch {
            val item = withContext(Dispatchers.IO) {

                homeRepository.getAllVM()
            }
            createData(item)
        }

    }

    private fun createData(item: List<VendingMachineEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            item.forEach {
                when (it.vm_name) {
                    Constants.SNACKS_VM -> {
                        snackData = withContext(Dispatchers.IO) {
                            homeRepository.getSnackData(it.id)
                        }
                    }
                    Constants.BEER_VM -> {
                        beerData = withContext(Dispatchers.IO) {
                            homeRepository.getBeerData(it.id)
                        }
                    }
                    Constants.COFFEE_VM -> {
                        coffeeData = withContext(Dispatchers.IO) {
                            homeRepository.getCoffeeData(it.id)
                        }
                    }
                }
            }
                Transformer().transformData(snackData, beerData, coffeeData, mListItem)

        }
    }
}