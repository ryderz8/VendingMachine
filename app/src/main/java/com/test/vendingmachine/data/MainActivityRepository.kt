package com.test.vendingmachine.data

import com.test.repository.VendingMachineDB
import com.test.repository.entity.BeerVM
import com.test.repository.entity.CoffeeVM
import com.test.repository.entity.SnacksVM
import com.test.repository.entity.VendingMachineEntity
import com.test.vendingmachine.utilities.Constants
import kotlinx.coroutines.coroutineScope
import java.util.*


class MainActivityRepository(private val vendingMachineDB: VendingMachineDB) :
    MainActivityRepoContract/*, CoroutineScope */ {

//    override val coroutineContext: CoroutineContext
//        get() = Dispatchers.Main


    companion object {
        @Volatile
        private var instance: MainActivityRepository? = null

        fun getInstance(vendingMachineDB: VendingMachineDB) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: MainActivityRepository(
                            vendingMachineDB
                        ).also { instance = it }
                }
    }

    override suspend fun insertScannedData(name: String) =
        coroutineScope {
            val latlng = Constants.getRandomLatLong()
            val uuid = UUID.randomUUID().toString()
            val res = vendingMachineDB.roomMainDao()
                .insertData(
                    VendingMachineEntity(
                        name,
                        uuid,
                        latlng.lat,
                        latlng.long
                    )
                )
            if (res > 0) {
                return@coroutineScope uuid
            } else {
                return@coroutineScope "0"
            }

        }

    override suspend fun insertSnacksData(vendorId: String) = coroutineScope {
        return@coroutineScope vendingMachineDB.snacksDao().insertData(
            SnacksVM(
                UUID.randomUUID().toString(),
                vendorId,
                "Parle G",
                "Parle biscuit",
                5,
                2
            )
        )
    }

    override suspend fun insertBeerData(vendorId: String) = coroutineScope {
        return@coroutineScope vendingMachineDB.beerDao().insertData(
            BeerVM(
                UUID.randomUUID().toString(),
                vendorId, "Carlsberg", 200, 4
            )
        )
    }

    override suspend fun insertCoffeeData(vendorId: String) = coroutineScope {
        return@coroutineScope vendingMachineDB.coffeeDao().insertData(
            CoffeeVM(
                UUID.randomUUID().toString(),
                vendorId, 5
            )
        )
    }
}
