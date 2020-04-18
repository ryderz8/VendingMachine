package com.test.vendingmachine.data.repository

import androidx.lifecycle.LiveData
import com.test.repository.VendingMachineDB
import com.test.repository.dao.MainDAO
import com.test.repository.entity.BeerVM
import com.test.repository.entity.SnacksVM
import com.test.repository.entity.VendingMachineEntity
import kotlinx.coroutines.coroutineScope

class HomeRepository(private val vendingMachineDB: VendingMachineDB) {

    companion object {
        @Volatile
        private var instance: HomeRepository? = null

        fun getInstance(vendingMachineDB: VendingMachineDB) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: HomeRepository(
                            vendingMachineDB
                        )
                            .also { instance = it }
                }
    }

    val allItem: LiveData<List<VendingMachineEntity>> =
        vendingMachineDB.roomMainDao().getAllCategory()

    suspend fun getSnackData(venderId: String): LiveData<List<SnacksVM>> = coroutineScope {
        return@coroutineScope vendingMachineDB.roomMainDao().getChild(venderId)
    }

}