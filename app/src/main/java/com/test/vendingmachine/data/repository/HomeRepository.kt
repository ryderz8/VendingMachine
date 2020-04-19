package com.test.vendingmachine.data.repository

import com.test.repository.VendingMachineDB
import com.test.repository.model.ItemVMBeer
import com.test.repository.model.ItemVMCoffee
import com.test.repository.model.ItemVMSnacks
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



    suspend fun getAllVM() : List<VendingMachineEntity> = coroutineScope {
        return@coroutineScope vendingMachineDB.roomMainDao().getAllCategory()
    }

    suspend fun getSnackData(venderId: String): List<ItemVMSnacks> = coroutineScope {
        return@coroutineScope vendingMachineDB.snacksDao().getChild(venderId)
    }

    suspend fun getBeerData(venderId: String): List<ItemVMBeer> = coroutineScope {
        return@coroutineScope vendingMachineDB.beerDao().getVMBeer(venderId)
    }

    suspend fun getCoffeeData(venderId: String): List<ItemVMCoffee> = coroutineScope {
        return@coroutineScope vendingMachineDB.coffeeDao().getVMCoffee(venderId)
    }
}