package com.test.repository.model

import androidx.room.Embedded
import com.test.repository.entity.BeerVM
import com.test.repository.entity.VendingMachineEntity

data class ItemVMBeer(
    @Embedded val vendingMachineEntity: VendingMachineEntity,
    @Embedded val beerVM: BeerVM
)