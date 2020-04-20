package com.test.vendingmachine.utilities

import androidx.lifecycle.MutableLiveData
import com.test.repository.model.ItemVMBeer
import com.test.repository.model.ItemVMCoffee
import com.test.repository.model.ItemVMSnacks
import com.test.repository.entity.BeerVM
import com.test.repository.entity.CoffeeVM
import com.test.repository.entity.SnacksVM
import java.lang.Exception

class Transformer {
    private var mainData = mutableListOf<Any>()

    fun transformData(
        snackData: List<ItemVMSnacks>,
        beerData: List<ItemVMBeer>,
        coffeeData: List<ItemVMCoffee>,
        mListItem: MutableLiveData<List<Any>>
    ) {
        try {
            val ivs = mutableListOf<SnacksVM>()
            val ivb = mutableListOf<BeerVM>()
            val ivc = mutableListOf<CoffeeVM>()

            var det: VMDetail? = null
            if (snackData.isNotEmpty()) {
                snackData.forEach { item ->
                    det = VMDetail(
                        item.vendingMachineEntity.vm_name,
                        item.vendingMachineEntity.lat,
                        item.vendingMachineEntity.ong
                    )
                    ivs.add(item.snacksVM)
                }
                mainData.add(ItemSnacks(det!!, ivs))
            }
            if(beerData.isNotEmpty()) {
                beerData.forEach { item ->
                    det = VMDetail(
                        item.vendingMachineEntity.vm_name,
                        item.vendingMachineEntity.lat,
                        item.vendingMachineEntity.ong
                    )
                    ivb.add(item.beerVM)
                }
                mainData.add(ItemBeer(det!!, ivb))
            }
            if(coffeeData.isNotEmpty()) {
                coffeeData.forEach { item ->
                    det = VMDetail(
                        item.vendingMachineEntity.vm_name,
                        item.vendingMachineEntity.lat,
                        item.vendingMachineEntity.ong
                    )

                    ivc.add(item.coffeeVM)
                }
                mainData.add(ItemCoffee(det!!, ivc))
            }

            mListItem.postValue(mainData)
        } catch (ex: Exception) {

        }
    }
}

data class ItemSnacks(
    val vn_detail: VMDetail,
    val snackData: List<SnacksVM>
)

data class ItemBeer(
    val vn_detail: VMDetail,
    val beerData: List<BeerVM>
)

data class ItemCoffee(
    val vn_detail: VMDetail,
    val coffeeData: List<CoffeeVM>
)

data class VMDetail(
    val vn_name: String,
    val lat: Double,
    val long: Double
)
