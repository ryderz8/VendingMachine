package com.test.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.repository.entity.CoffeeVM

@Dao
interface CoffeeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(coffeeVM: CoffeeVM) : Long

    @Query("SELECT * FROM coffee")
    fun getAllCategory(): LiveData<List<CoffeeVM>>
}