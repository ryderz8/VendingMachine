package com.test.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Extension function to use the Observer when the LifeCycleOwner cannot be used<br/>
 * This observer will receive only one event and then it removes itself
 */
fun <T> LiveData<T>.observeOnce(observer: Observer<T>) {
    observeForever(object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}