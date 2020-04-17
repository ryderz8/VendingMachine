package com.test.vendingmachine.utilities

import com.google.android.gms.maps.model.LatLng
import com.test.vendingmachine.R
import java.util.*

class Constants {

    companion object {

        const val BAR_CODE = "BarCode"
        const val BAR_CODE_NOT_FOUND = "Bar code not found"
        const val ERROR = "Error"
        const val HANDLER = "handler"
        const val PRESS_AGAIN_TO_SCAN = "Press again button to scan code"
        const val SCAN_ERROR_SOUND = R.raw.computer_error
        const val SCAN_OK_SOUND = R.raw.beep_normal
        const val STOP_CAMERA = "************** Stop Camera**********"

        const val COFFEE_VM = "coffee"
        const val SNACKS_VM = "snacks"
        const val BEER_VM =  "beer"

        fun getRandomLatLong() : LatLong {
            val list = listOf(
                LatLong(19.0760, 72.8777), LatLong(28.7041, 77.1025), LatLong(12.9716, 77.5946),
                LatLong(18.5204, 73.8567)
            )
            return list.random()
        }

    }
}

data class LatLong(val lat: Double, val long: Double)
