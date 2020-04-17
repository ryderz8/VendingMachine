package com.test.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "snacks", indices = [Index(value = ["name"], unique = true)])
data class SnacksVM(
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "vendor_id") val vendorId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "price") val price: Long,
    @ColumnInfo(name = "quantity") val quantity: Long
)