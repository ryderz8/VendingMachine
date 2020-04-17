package com.test.vendingmachine.data

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers


class MainInteractor(private val mainActivityRepoContract: MainActivityRepoContract) :
    MainActivityUseCase {

    override fun insertScannedData(name: String) {
        liveData(Dispatchers.IO) {
            emit(mainActivityRepoContract.insertScannedData(name))
        }
    }

}