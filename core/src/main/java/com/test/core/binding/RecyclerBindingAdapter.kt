package com.test.core.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

interface RecyclerBindingContract<T> {
    fun setData(data: T)
}

@BindingAdapter("itemData")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T) {
    if (data != null && recyclerView.adapter is RecyclerBindingContract<*>) {
        (recyclerView.adapter as RecyclerBindingContract<T>).setData(data)
    }
}