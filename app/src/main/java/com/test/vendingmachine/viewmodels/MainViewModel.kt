package com.test.vendingmachine.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.core.base.BaseViewModel
import com.test.vendingmachine.data.MainActivityRepoContract
import com.test.vendingmachine.utilities.Constants
import kotlinx.coroutines.launch

class MainViewModel(private val mainActivityRepoContract: MainActivityRepoContract) :
    BaseViewModel() {

    var valueInserted = MutableLiveData<Long>().apply { value = -1 }


    fun addScannedEntryToDB(vmName: String) {
        viewModelScope.launch {
            val resId = mainActivityRepoContract.insertScannedData(vmName)
            if (resId != "0") {
                when (vmName) {
                    Constants.SNACKS_VM ->
                        mainActivityRepoContract.insertSnacksData(resId)
                    Constants.BEER_VM ->
                        mainActivityRepoContract.insertBeerData(resId)
                    Constants.COFFEE_VM ->
                        mainActivityRepoContract.insertCoffeeData(resId)
                }
            }
        }
    }

}