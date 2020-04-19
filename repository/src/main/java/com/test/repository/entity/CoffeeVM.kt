package com.test.repository.entity

import androidx.room.*

@Entity(
    tableName = "coffee", foreignKeys = [ForeignKey(
        entity = VendingMachineEntity::class,
        parentColumns = ["id"],
        childColumns = ["vendor_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CoffeeVM(
    @ColumnInfo(name = "coffee_id") @PrimaryKey val coffee_id: String,
    @ColumnInfo(name = "vendor_id") val vendorId: String,
    @ColumnInfo(name = "litre") var litre: Int
)