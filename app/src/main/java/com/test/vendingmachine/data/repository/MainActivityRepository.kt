package com.test.vendingmachine.data.repository

import androidx.lifecycle.LiveData
import com.test.repository.VendingMachineDB
import com.test.repository.dao.BeerDAO
import com.test.repository.dao.CoffeeDAO
import com.test.repository.dao.MainDAO
import com.test.repository.dao.SnacksDAO
import com.test.repository.entity.BeerVM
import com.test.repository.entity.CoffeeVM
import com.test.repository.entity.SnacksVM
import com.test.repository.entity.VendingMachineEntity
import com.test.vendingmachine.utilities.Constants
import kotlinx.coroutines.coroutineScope
import java.util.*


class MainActivityRepository(
    private val vendingMachineDB: VendingMachineDB
) {

    companion object {
        @Volatile
        private var instance: MainActivityRepository? = null

        fun getInstance(
            vendingMachineDB: VendingMachineDB
        ) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: MainActivityRepository(
                            vendingMachineDB
                        )
                            .also { instance = it }
                }
    }

    suspend fun insertScannedData(name: String): String {

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
        return if (res > 0) {
            uuid
        } else {
            "0"
        }
    }

    suspend fun insertSnacksData(vendorId: String) {
        vendingMachineDB.snacksDao().insertData(
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

    suspend fun insertBeerData(vendorId: String) {
        vendingMachineDB.beerDao().insertData(
            BeerVM(
                UUID.randomUUID().toString(),
                vendorId, "Carlsberg", 200, 4
            )
        )
    }

    suspend fun insertCoffeeData(vendorId: String) {
        vendingMachineDB.coffeeDao().insertData(
            CoffeeVM(
                UUID.randomUUID().toString(),
                vendorId, 5
            )
        )
    }

    suspend fun getAllItem() : LiveData<List<VendingMachineEntity>> = coroutineScope {
        return@coroutineScope vendingMachineDB.roomMainDao().getAllCategory()
    }
}
