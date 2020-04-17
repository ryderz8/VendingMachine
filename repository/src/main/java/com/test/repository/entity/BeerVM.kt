package com.test.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "beer", indices = [Index(value = ["beer_brand"], unique = true)])
data class BeerVM(@ColumnInfo(name = "id") @PrimaryKey val id: String,
                  @ColumnInfo(name = "vendor_id") val vendorId: String,
                  @ColumnInfo(name = "beer_brand") val beer_brand: String,
                  @ColumnInfo(name = "price") val price: Long,
                  @ColumnInfo(name = "quantity") val quantity: Long

)