package com.test.beervm.data

import android.content.Context
import com.test.beervm.viewmodels.BeerViewModelFactory
import com.test.repository.VendingMachineDB

object DependencyProvider {

    private fun provideDatabase(context: Context): VendingMachineDB {
        return VendingMachineDB.getDatabase(context)
    }

    fun provideBeerViewModelFactory(context: Context): BeerViewModelFactory {
        val usecase = getSnacksRepository(context)
        return BeerViewModelFactory(usecase)
    }

    private fun getSnacksRepository(context: Context): IBeerRepository {
        return BeerRepository.getInstance(provideDatabase(context))
    }

}