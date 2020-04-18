package com.test.coffeevm.data

import android.content.Context
import com.test.coffeevm.viewmodels.CoffeeViewModelFactory
import com.test.repository.VendingMachineDB
import com.test.repository.dao.CoffeeDAO

object DependencyProvider {

    private fun provideDao(context: Context): CoffeeDAO {
        return VendingMachineDB.getDatabase(context).coffeeDao()
    }

    fun provideCoffeeViewModelFactory(context: Context): CoffeeViewModelFactory {
        val usecase = getCoffeeRepository(context)
        return CoffeeViewModelFactory(usecase)
    }

    private fun getCoffeeRepository(context: Context): CoffeeRepository{
        return CoffeeRepository.getInstance(provideDao(context))
    }


}
