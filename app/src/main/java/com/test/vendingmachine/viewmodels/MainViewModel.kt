package com.test.vendingmachine.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.test.core.base.BaseViewModel
import com.test.core.observeOnce
import com.test.vendingmachine.data.repository.MainActivityRepository
import com.test.vendingmachine.utilities.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainActivityRepository: MainActivityRepository) :
    BaseViewModel() {

    var valueInserted = MutableLiveData<Long>().apply { value = -1 }

    fun addScannedEntryToDB(vmName: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val resId = mainActivityRepository.insertScannedData(vmName)
            if (resId != "0") {
                valueInserted.postValue(1)
                when (vmName) {
                    Constants.SNACKS_VM ->
                        mainActivityRepository.insertSnacksData(resId)
                    Constants.BEER_VM ->
                        mainActivityRepository.insertBeerData(resId)
                    Constants.COFFEE_VM ->
                        mainActivityRepository.insertCoffeeData(resId)
                }
            }
        }
    }

    fun checkForEmptyDB() {
        viewModelScope.launch {
//            val res = mainActivityRepository.getAllItem()
//            res.observeOnce(Observer {
//                if (it?.size ?: 0 > 0) {
//                    valueInserted.value = 1
//                }
//            })

        }
    }

}