package com.test.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.repository.entity.BeerVM
import com.test.repository.model.ItemVMBeer

@Dao
interface BeerDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(beerVM: BeerVM) : Long

    @Query("SELECT * FROM beer")
    fun getAllCategory(): LiveData<List<BeerVM>>

    @Query("SELECT * FROM beer INNER JOIN Vending_Machines ON beer.vendor_id = Vending_machines.id WHERE beer.vendor_id=:venderId LIMIT 2")
    fun getVMBeer(venderId: String): List<ItemVMBeer>
}