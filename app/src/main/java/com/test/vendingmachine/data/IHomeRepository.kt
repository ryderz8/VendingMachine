package com.test.vendingmachine.data

import androidx.lifecycle.LiveData
import com.test.repository.entity.VendingMachineEntity

interface IHomeRepository {
    suspend fun getAllItem(): LiveData<List<VendingMachineEntity>>

}