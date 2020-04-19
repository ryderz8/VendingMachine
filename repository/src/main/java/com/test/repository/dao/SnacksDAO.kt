package com.test.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.repository.entity.SnacksVM
import com.test.repository.model.ItemVMSnacks

@Dao
interface SnacksDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(snacksVM: SnacksVM): Long

    @Query("SELECT * FROM snacks")
    fun getAllCategory(): LiveData<List<SnacksVM>>

    @Query("SELECT * FROM snacks INNER JOIN Vending_Machines ON snacks.vendor_id = Vending_machines.id WHERE snacks.vendor_id=:venderId LIMIT 2")
    fun getChild(venderId: String): List<ItemVMSnacks>
}