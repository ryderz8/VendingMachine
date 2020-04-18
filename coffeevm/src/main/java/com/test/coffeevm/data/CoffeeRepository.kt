package com.test.coffeevm.data

import androidx.lifecycle.LiveData
import com.test.repository.VendingMachineDB
import com.test.repository.dao.CoffeeDAO
import com.test.repository.entity.CoffeeVM
import kotlinx.coroutines.coroutineScope

class CoffeeRepository(private val coffeeDAO: CoffeeDAO) {

    companion object {
        @Volatile
        private var instance: CoffeeRepository? = null

        fun getInstance(coffeeDAO: CoffeeDAO) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: CoffeeRepository(
                            coffeeDAO
                        ).also { instance = it }
                }
    }

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
     suspend fun insertScannedData(coffeeVM: CoffeeVM) {
         coffeeDAO.insertData(coffeeVM)
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allItem : LiveData<List<CoffeeVM>> = coffeeDAO.getAllCategory()

}

