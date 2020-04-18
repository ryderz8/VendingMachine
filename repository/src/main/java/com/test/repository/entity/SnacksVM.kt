package com.test.repository.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "snacks", foreignKeys = [ForeignKey(
        entity = VendingMachineEntity::class,
        parentColumns = ["id"],
        childColumns = ["vendor_id"],
        onDelete = CASCADE
    )], indices = [Index(value = ["name"], unique = true)]
)
data class SnacksVM(
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "vendor_id") val vendorId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "quantity") val quantity: Int
)