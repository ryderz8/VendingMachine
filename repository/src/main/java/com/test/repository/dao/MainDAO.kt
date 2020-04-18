package com.test.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.repository.entity.SnacksVM
import com.test.repository.entity.VendingMachineEntity

@Dao
interface MainDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(vendingMachineEntity: VendingMachineEntity): Long

    @Query("SELECT * FROM Vending_Machines")
    fun getAllCategory(): LiveData<List<VendingMachineEntity>>

    @Query("SELECT * FROM snacks WHERE vendor_id = :venderId")
    fun getChild(venderId: String): LiveData<List<SnacksVM>>

}