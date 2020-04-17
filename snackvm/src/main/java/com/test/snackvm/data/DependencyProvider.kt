package com.test.snackvm.data

import android.content.Context
import com.test.repository.VendingMachineDB
import com.test.snackvm.viewmodel.SnacksViewModelFactory

object DependencyProvider {

    private fun provideDatabase(context: Context): VendingMachineDB {
        return VendingMachineDB.getDatabase(context)
    }
    fun provideSnacksViewModelFactory(context: Context): SnacksViewModelFactory {
        val usecase = getSnacksRepository(context)
        return SnacksViewModelFactory(usecase)
    }

    private fun getSnacksRepository(context: Context): ISnacksRepository {
        return SnacksRepository.getInstance(provideDatabase(context))
    }

}