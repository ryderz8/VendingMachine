package com.test.snackvm.data

import android.content.Context
import com.test.repository.VendingMachineDB
import com.test.repository.dao.SnacksDAO
import com.test.snackvm.viewmodel.SnacksViewModelFactory

object DependencyProvider {

    private fun provideDatabase(context: Context): SnacksDAO {
        return VendingMachineDB.getDatabase(context).snacksDao()
    }
    fun provideSnacksViewModelFactory(context: Context): SnacksViewModelFactory {
        val usecase = getSnacksRepository(context)
        return SnacksViewModelFactory(usecase)
    }

    private fun getSnacksRepository(context: Context): SnacksRepository {
        return SnacksRepository.getInstance(provideDatabase(context))
    }

}