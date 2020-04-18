package com.test.vendingmachine.utilities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.repository.entity.BeerVM
import com.test.repository.entity.SnacksVM
import com.test.vendingmachine.data.Item
import com.test.vendingmachine.data.ItemData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class Transformer{
    private val couroutineScope = CoroutineScope(Dispatchers.IO)


    fun createData(
        value: LiveData<List<SnacksVM>>,
        it: LiveData<List<BeerVM>>,
        listDataItem: MutableLiveData<List<ItemData>>
    ){
        val resList = mutableListOf<ItemData>()

            if(value.value?.size ?:0 >0) resList.add(ItemData(Item(value.value?.get(0)?.name ?:"" ,value.value?.get(0)?.quantity.toString() ?: "")))
            if(it.value?.size ?: 0 > 0) resList.add(ItemData(Item(it.value?.get(0)?.beer_brand ?:"" ,
                it.value?.get(0)?.quantity.toString() ?: "")))
            listDataItem.postValue(resList)

    }

}