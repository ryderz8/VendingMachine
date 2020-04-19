package com.test.repository.model

import androidx.room.Embedded
import com.test.repository.entity.CoffeeVM
import com.test.repository.entity.VendingMachineEntity

data class ItemVMCoffee (
    @Embedded val vendingMachineEntity: VendingMachineEntity,
    @Embedded val coffeeVM: CoffeeVM
)