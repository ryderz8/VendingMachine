package com.test.coffeevm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.coffeevm.R
import com.test.coffeevm.BR
import com.test.coffeevm.databinding.CoffeeRowItemBinding
import com.test.repository.entity.CoffeeVM

class CoffeeItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    RecyclerBindingContract<List<CoffeeVM>> {

    var itemList = listOf<CoffeeVM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.coffee_row_item, parent, false
            )
        )
    }

    override fun setData(data: List<CoffeeVM>) {
        itemList = data
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemList[position].let { item ->
            (holder as ItemViewHolder).bindItem(item, position)
        }

    }

    inner class ItemViewHolder(private var rowItemBinding: CoffeeRowItemBinding) :
        RecyclerView.ViewHolder(rowItemBinding.root) {
        fun bindItem(data: CoffeeVM, position: Int) {
            rowItemBinding.setVariable(BR.data, data)
            rowItemBinding.executePendingBindings()

        }
    }
}
