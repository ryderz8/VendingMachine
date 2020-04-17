package com.test.vendingmachine.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.test.core.base.BaseViewModel
import com.test.core.observeOnce
import com.test.repository.entity.VendingMachineEntity
import com.test.vendingmachine.data.IHomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val iHomeRepository: IHomeRepository) : BaseViewModel() {

    val listItem: MutableLiveData<List<VendingMachineEntity>> = MutableLiveData()

    fun getListItem() {
        viewModelScope.launch {

            iHomeRepository.getAllItem().observeOnce(Observer {
                Log.i("size", "" + it.size)
                listItem.value = it
            })


        }
    }

}