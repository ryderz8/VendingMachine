package com.test.snackvm.data

import androidx.lifecycle.LiveData
import com.test.repository.entity.SnacksVM

interface ISnacksRepository {
    suspend fun insertScannedData(name : String) : Long
    suspend fun getAllItem(): LiveData<List<SnacksVM>>

}