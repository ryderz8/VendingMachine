package com.test.beervm.data

import android.content.Context
import com.test.beervm.viewmodels.BeerViewModelFactory
import com.test.repository.VendingMachineDB
import com.test.repository.dao.BeerDAO

object DependencyProvider {

    private fun provideDatabase(context: Context): BeerDAO {
        return VendingMachineDB.getDatabase(context).beerDao()
    }

    fun provideBeerViewModelFactory(context: Context): BeerViewModelFactory {
        val usecase = getSnacksRepository(context)
        return BeerViewModelFactory(usecase)
    }

    private fun getSnacksRepository(context: Context): BeerRepository {
        return BeerRepository.getInstance(provideDatabase(context))
    }

}