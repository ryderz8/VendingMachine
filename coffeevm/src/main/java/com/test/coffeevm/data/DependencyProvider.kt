package com.test.coffeevm.data

import android.content.Context
import com.test.coffeevm.viewmodels.CoffeeViewModelFactory
import com.test.repository.VendingMachineDB

object DependencyProvider {

    private fun provideDatabase(context: Context): VendingMachineDB {
        return VendingMachineDB.getDatabase(context)
    }

    fun provideCoffeeViewModelFactory(context: Context): CoffeeViewModelFactory {
        val usecase = getCoffeeRepository(context)
        return CoffeeViewModelFactory(usecase)
    }

    private fun getCoffeeRepository(context: Context): ICoffeeRepository {
        return CoffeeRepository.getInstance(provideDatabase(context))
    }


}
