package com.test.snackvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.test.core.base.BaseViewModel
import com.test.core.observeOnce
import com.test.repository.entity.SnacksVM
import com.test.snackvm.data.ISnacksRepository
import kotlinx.coroutines.launch

class SnacksViewModel(private val iSnacksRepository: ISnacksRepository) : BaseViewModel() {

    val listItem: MutableLiveData<List<SnacksVM>> = MutableLiveData()

    fun getListItem() {
        viewModelScope.launch {
            iSnacksRepository.getAllItem().observeOnce(Observer {
                Log.i("size", "" + it.size)
                listItem.value = it
            })
        }
    }

}