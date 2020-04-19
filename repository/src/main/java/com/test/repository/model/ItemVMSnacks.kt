package com.test.repository.model

import androidx.room.Embedded
import com.test.repository.entity.SnacksVM
import com.test.repository.entity.VendingMachineEntity

data class ItemVMSnacks(
    @Embedded val vendingMachineEntity: VendingMachineEntity,
    @Embedded val snacksVM: SnacksVM
)