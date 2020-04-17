package com.test.vendingmachine.utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentHelper {

    fun replaceFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {
        val transaction = manager.beginTransaction()
        transaction.replace(frameId, fragment,fragment::class.java.simpleName)
        transaction.commit()

    }

}