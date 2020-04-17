package com.test.coffeevm.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.test.coffeevm.data.ICoffeeRepository
import com.test.core.base.BaseViewModel
import com.test.core.observeOnce
import com.test.repository.entity.CoffeeVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeViewModel(private val iCoffeeRepository: ICoffeeRepository) : BaseViewModel() {
    val listItem: MutableLiveData<List<CoffeeVM>> = MutableLiveData()

    fun getListItem() {
        viewModelScope.launch {
            iCoffeeRepository.getAllItem().observeOnce(Observer {
                Log.i("size", "" + it.size)
                listItem.value = it
            })
        }
    }

    fun updateItem() {
        viewModelScope.launch(Dispatchers.IO) {
            listItem.value?.get(0)?.let {
                it.litre += 1
                iCoffeeRepository.insertScannedData(it)
            }
        }
    }
}