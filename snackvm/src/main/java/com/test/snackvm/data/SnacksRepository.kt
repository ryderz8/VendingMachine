package com.test.snackvm.data

import androidx.lifecycle.LiveData
import com.test.repository.dao.SnacksDAO
import com.test.repository.entity.SnacksVM

class SnacksRepository(private val snacksDAO: SnacksDAO)  {

    companion object {
        @Volatile
        private var instance: SnacksRepository? = null

        fun getInstance(snacksDAO: SnacksDAO) =
            instance
                ?: synchronized(this) {
                    instance
                        ?: SnacksRepository(
                            snacksDAO
                        ).also { instance = it }
                }
    }

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.

    suspend fun insertScannedData(snacksVM: SnacksVM) {
        snacksDAO.insertData(snacksVM)
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allItem: LiveData<List<SnacksVM>> = snacksDAO.getAllCategory()


}


