package com.test.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.repository.entity.SnacksVM

@Dao
interface SnacksDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(snacksVM: SnacksVM) : Long

    @Query("SELECT * FROM snacks")
    fun getAllCategory(): LiveData<List<SnacksVM>>

}