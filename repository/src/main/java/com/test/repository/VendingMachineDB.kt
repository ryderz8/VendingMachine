package com.test.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.repository.dao.BeerDAO
import com.test.repository.dao.CoffeeDAO
import com.test.repository.dao.MainDAO
import com.test.repository.dao.SnacksDAO
import com.test.repository.entity.BeerVM
import com.test.repository.entity.CoffeeVM
import com.test.repository.entity.SnacksVM
import com.test.repository.entity.VendingMachineEntity

@Database(entities = [VendingMachineEntity::class, BeerVM::class, SnacksVM::class, CoffeeVM::class], version = 1, exportSchema = false)
abstract class VendingMachineDB : RoomDatabase() {

    abstract fun roomMainDao() : MainDAO
    abstract fun snacksDao() : SnacksDAO
    abstract fun beerDao() : BeerDAO
    abstract fun coffeeDao() : CoffeeDAO

    companion object {
        @Volatile
        private var INSTANCE: VendingMachineDB? = null

        fun getDatabase(context: Context): VendingMachineDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VendingMachineDB::class.java,
                    "vendingmachine_db"
                ).fallbackToDestructiveMigration()
                 .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}