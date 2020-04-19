package com.test.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.repository.entity.CoffeeVM
import com.test.repository.model.ItemVMCoffee

@Dao
interface CoffeeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(coffeeVM: CoffeeVM) : Long

    @Query("SELECT * FROM coffee")
    fun getAllCategory(): LiveData<List<CoffeeVM>>

    @Query("SELECT * FROM coffee INNER JOIN Vending_Machines ON coffee.vendor_id = Vending_machines.id WHERE coffee.vendor_id=:venderId LIMIT 2")
    fun getVMCoffee(venderId: String): List<ItemVMCoffee>
}