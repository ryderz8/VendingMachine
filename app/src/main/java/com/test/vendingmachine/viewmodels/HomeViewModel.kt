package com.test.vendingmachine.viewmodels

import androidx.lifecycle.LiveData
import com.test.core.base.BaseViewModel
import com.test.repository.entity.VendingMachineEntity
import com.test.vendingmachine.data.repository.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel() {

    val listItem: LiveData<List<VendingMachineEntity>> = homeRepository.allItem

}