package com.test.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "Vending_Machines"/*, foreignKeys = [ForeignKey(
        entity = SnacksVM::class,
        parentColumns = ["vendor_id"],
        childColumns = ["id"],
        onDelete = CASCADE
    ),
        ForeignKey(
            entity = BeerVM::class,
            parentColumns = ["vendor_id"],
            childColumns = ["id"],
            onDelete = CASCADE
        )
    ]*/
    , indices = [Index(value = ["name"], unique = true)]
)
data class VendingMachineEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "long") val ong: Double
)