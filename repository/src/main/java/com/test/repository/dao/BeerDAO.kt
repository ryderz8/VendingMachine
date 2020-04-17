package com.test.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.repository.entity.BeerVM

@Dao
interface BeerDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(beerVM: BeerVM) : Long

    @Query("SELECT * FROM beer")
    fun getAllCategory(): LiveData<List<BeerVM>>
}