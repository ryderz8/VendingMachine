package com.test.beervm.data

import androidx.lifecycle.LiveData
import com.test.repository.VendingMachineDB
import com.test.repository.entity.BeerVM
import kotlinx.coroutines.coroutineScope

class BeerRepository(private val vendingMachineDB: VendingMachineDB) : IBeerRepository {

    companion object {
        @Volatile
        private var instance: BeerRepository? = null

        fun getInstance(vendingMachineDB: VendingMachineDB) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: BeerRepository(
                            vendingMachineDB
                        ).also { instance = it }
                }
    }


    override suspend fun insertScannedData(name: String): Long {
        return 1
    }

    override suspend fun getAllItem(): LiveData<List<BeerVM>> = coroutineScope {
        return@coroutineScope vendingMachineDB.beerDao().getAllCategory()
    }
}

