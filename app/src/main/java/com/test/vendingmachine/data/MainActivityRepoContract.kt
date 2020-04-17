package com.test.vendingmachine.data

interface MainActivityRepoContract {
    suspend fun insertScannedData(name: String): String
    suspend fun insertSnacksData(vendorId: String) : Long
    suspend fun insertBeerData(vendorId: String) : Long
    suspend fun insertCoffeeData(vendorId: String) : Long
}