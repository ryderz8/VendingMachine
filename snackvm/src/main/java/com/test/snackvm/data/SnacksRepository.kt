package com.test.snackvm.data

import androidx.lifecycle.LiveData
import com.test.repository.VendingMachineDB
import com.test.repository.entity.SnacksVM
import kotlinx.coroutines.coroutineScope

class SnacksRepository(private val vendingMachineDB: VendingMachineDB) : ISnacksRepository {

    companion object {
        @Volatile
        private var instance: SnacksRepository? = null

        fun getInstance(vendingMachineDB: VendingMachineDB) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: SnacksRepository(
                            vendingMachineDB
                        ).also { instance = it }
                }
    }


    override suspend fun insertScannedData(name: String): Long {
        return 1
    }

    override suspend fun getAllItem(): LiveData<List<SnacksVM>> = coroutineScope {
        return@coroutineScope vendingMachineDB.snacksDao().getAllCategory()
    }
}


