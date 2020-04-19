package com.test.repository.entity

import androidx.room.*

@Entity(
    tableName = "beer", foreignKeys = [ForeignKey(
        entity = VendingMachineEntity::class,
        parentColumns = ["id"],
        childColumns = ["vendor_id"],
        onDelete = ForeignKey.CASCADE
    )], indices = [Index(value = ["beer_brand"], unique = true)]
)
data class BeerVM(
    @ColumnInfo(name = "beer_id") @PrimaryKey val beer_id: String,
    @ColumnInfo(name = "vendor_id") val vendorId: String,
    @ColumnInfo(name = "beer_brand") val beer_brand: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "quantity") val quantity: Int

)