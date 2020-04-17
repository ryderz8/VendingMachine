package com.test.coffeevm.data

import androidx.lifecycle.LiveData
import com.test.repository.entity.CoffeeVM

interface ICoffeeRepository {
    suspend fun insertScannedData(coffeeVM: CoffeeVM) : Long
    suspend fun getAllItem(): LiveData<List<CoffeeVM>>
}