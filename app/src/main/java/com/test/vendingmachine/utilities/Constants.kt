package com.test.vendingmachine.utilities

import com.test.vendingmachine.R

class Constants {

    companion object {

        const val BAR_CODE = "BarCode"
        const val BAR_CODE_NOT_FOUND = "Bar code not found"
        const val ERROR = "Error"
        const val HANDLER = "handler"
        const val PRESS_AGAIN_TO_SCAN = "Press again button to scan code"
        const val STOP_CAMERA = "************** Stop Camera**********"

        const val COFFEE_VM = "coffee"
        const val SNACKS_VM = "snacks"
        const val BEER_VM = "beer"
        const val COFFEE = 2
        const val SNACKS= 0
        const val BEER= 1
        var randomCount = -1

        fun getRandomLatLong(): LatLong {
            val list = listOf(
                LatLong(19.0760, 72.8777), LatLong(28.7041, 77.1025), LatLong(12.9716, 77.5946)
            )
            return if (randomCount <= 1) {
                randomCount++
                list[randomCount]
            } else {
                randomCount = 0
                list[randomCount]
            }
        }

    }
}

data class LatLong(val lat: Double, val long: Double)
