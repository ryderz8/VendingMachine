package com.test.vendingmachine.data.di

import android.content.Context
import com.test.repository.VendingMachineDB
import com.test.vendingmachine.data.repository.HomeRepository
import com.test.vendingmachine.data.repository.MainActivityRepository
import com.test.vendingmachine.viewmodels.HomeViewModelFactory
import com.test.vendingmachine.viewmodels.MainViewModelFactory

object DependencyProvider {


    private fun provideDatabase(context: Context) : VendingMachineDB {
        return VendingMachineDB.getDatabase(context)
    }


    fun provideMainViewModelFactory(context: Context): MainViewModelFactory {
        val usecase =
            getMainRepository(
                context
            )
        return MainViewModelFactory(usecase)
    }

    private fun getMainRepository(context: Context): MainActivityRepository {
        return MainActivityRepository.getInstance(
        provideDatabase(context)
        )
    }

    fun provideHomeViewModelFactory(context: Context) : HomeViewModelFactory {
        val useCase =
            getHomeRepository(
                context
            )
        return HomeViewModelFactory(useCase)
    }

    private fun getHomeRepository(context: Context) : HomeRepository {
        return HomeRepository.getInstance(
            provideDatabase(
                context
            )
        )
    }

}