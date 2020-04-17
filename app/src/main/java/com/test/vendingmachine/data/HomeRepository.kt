package com.test.vendingmachine.data

import androidx.lifecycle.LiveData
import com.test.repository.VendingMachineDB
import com.test.repository.entity.VendingMachineEntity
import kotlinx.coroutines.coroutineScope

class HomeRepository(private val vendingMachineDB: VendingMachineDB) : IHomeRepository {

    companion object {
        @Volatile
        private var instance: HomeRepository? = null

        fun getInstance(vendingMachineDB: VendingMachineDB) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: HomeRepository(
                            vendingMachineDB
                        ).also { instance = it }
                }
    }

    override suspend fun getAllItem(): LiveData<List<VendingMachineEntity>> = coroutineScope {
        return@coroutineScope vendingMachineDB.roomMainDao().getAllCategory()
    }

}