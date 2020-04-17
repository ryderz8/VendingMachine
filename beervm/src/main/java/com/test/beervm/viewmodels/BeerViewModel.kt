package com.test.beervm.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.test.beervm.data.IBeerRepository
import com.test.core.base.BaseViewModel
import com.test.core.observeOnce
import com.test.repository.entity.BeerVM
import kotlinx.coroutines.launch

class BeerViewModel(private val iBeerRepository: IBeerRepository) : BaseViewModel() {

    val listItem: MutableLiveData<List<BeerVM>> = MutableLiveData()

    fun getListItem() {
        viewModelScope.launch {
            iBeerRepository.getAllItem().observeOnce(Observer {
                Log.i("size", "" + it.size)
                listItem.value = it
            })
        }
    }

}