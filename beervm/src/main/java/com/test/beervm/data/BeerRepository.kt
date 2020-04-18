package com.test.beervm.data

import androidx.lifecycle.LiveData
import com.test.repository.VendingMachineDB
import com.test.repository.dao.BeerDAO
import com.test.repository.entity.BeerVM
import kotlinx.coroutines.coroutineScope

class BeerRepository(private val beerDAO: BeerDAO) {

    companion object {
        @Volatile
        private var instance: BeerRepository? = null

        fun getInstance(beerDAO: BeerDAO) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: BeerRepository(
                            beerDAO
                        ).also { instance = it }
                }
    }


    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    suspend fun insertScannedData(beerVM: BeerVM) {
        beerDAO.insertData(beerVM)
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allItem : LiveData<List<BeerVM>> = beerDAO.getAllCategory()

}

