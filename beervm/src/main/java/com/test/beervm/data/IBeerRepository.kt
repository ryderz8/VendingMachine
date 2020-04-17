package com.test.beervm.data

import androidx.lifecycle.LiveData
import com.test.repository.entity.BeerVM

interface IBeerRepository {
    suspend fun insertScannedData(name : String) : Long
    suspend fun getAllItem(): LiveData<List<BeerVM>>
}