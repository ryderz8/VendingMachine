package com.test.repository.entity

import androidx.room.*


@Entity(
    tableName = "Vending_Machines", indices = [Index(value = ["vm_name"], unique = true)]
)
data class VendingMachineEntity(
    @ColumnInfo(name = "vm_name") val vm_name: String,
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "long") val ong: Double
)