package com.test.coffeevm.data

import androidx.lifecycle.LiveData
import com.test.repository.VendingMachineDB
import com.test.repository.entity.CoffeeVM
import kotlinx.coroutines.coroutineScope

class CoffeeRepository(private val vendingMachineDB: VendingMachineDB) : ICoffeeRepository {

    companion object {
        @Volatile
        private var instance: CoffeeRepository? = null

        fun getInstance(vendingMachineDB: VendingMachineDB) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: CoffeeRepository(
                            vendingMachineDB
                        ).also { instance = it }
                }
    }

    override suspend fun insertScannedData(coffeeVM: CoffeeVM) = coroutineScope {
        return@coroutineScope vendingMachineDB.coffeeDao().insertData(coffeeVM)
    }


    override suspend fun getAllItem(): LiveData<List<CoffeeVM>> = coroutineScope {
        return@coroutineScope vendingMachineDB.coffeeDao().getAllCategory()
    }
}

