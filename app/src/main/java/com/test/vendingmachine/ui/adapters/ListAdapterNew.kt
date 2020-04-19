package com.test.vendingmachine.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.repository.entity.VendingMachineEntity
import com.test.vendingmachine.BR
import com.test.vendingmachine.R
import com.test.vendingmachine.databinding.RowItemBeerBinding
import com.test.vendingmachine.databinding.RowItemCoffeeBinding
import com.test.vendingmachine.databinding.RowItemNewBinding
import com.test.vendingmachine.utilities.Constants.Companion.BEER
import com.test.vendingmachine.utilities.Constants.Companion.COFFEE
import com.test.vendingmachine.utilities.Constants.Companion.SNACKS
import com.test.vendingmachine.utilities.ItemBeer
import com.test.vendingmachine.utilities.ItemCoffee
import com.test.vendingmachine.utilities.ItemSnacks

class ListAdapterNew : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    RecyclerBindingContract<List<Any>> {

    var itemList = listOf<Any>()

    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SNACKS -> {
                ItemViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.row_item_new, parent, false
                    ), parent.context!!
                )
            }
            BEER -> {
                ItemBeerViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.row_item_beer, parent, false
                    ), parent.context!!
                )
            }
            COFFEE -> {
                ItemCoffeeViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.row_item_coffee, parent, false
                    ), parent.context!!
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun setData(data: List<Any>) {
        itemList = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = itemList[position]
        return when (comparable) {
            is ItemSnacks -> {
                SNACKS
            }
            is ItemCoffee -> {
                COFFEE
            }
            is ItemBeer -> {
                BEER
            }
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                itemList[position].let { item ->
                    (holder as ItemViewHolder).bindItem(item as ItemSnacks, position)
                }
            }
            is ItemBeerViewHolder -> {
                itemList[position].let { item ->
                    (holder as ItemBeerViewHolder).bindItem(item as ItemBeer, position)
                }
            }
            is ItemCoffeeViewHolder -> {
                itemList[position].let { item ->
                    (holder as ItemCoffeeViewHolder).bindItem(item as ItemCoffee, position)
                }
            }
        }


    }

    inner class ItemViewHolder(
        private var rowItemBinding: RowItemNewBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(rowItemBinding.root) {
        fun bindItem(data: ItemSnacks, position: Int) {
            rowItemBinding.setVariable(BR.data, data)

            val linearLayout = rowItemBinding.linearId
            linearLayout.removeAllViews();

            data.snackData.forEach {
                val textView = TextView(context)
                textView.id = View.generateViewId()
                textView.text = it.name.plus("  ").plus(it.quantity.toString())
                linearLayout.addView(textView)
            }
            rowItemBinding.executePendingBindings()

            rowItemBinding.cardView.setOnClickListener {
                 onItemClick?.invoke(data.vn_detail.vn_name)
            }
        }
    }

    inner class ItemBeerViewHolder(
        private var rowItemBinding: RowItemBeerBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(rowItemBinding.root) {
        fun bindItem(data: ItemBeer, position: Int) {
            rowItemBinding.setVariable(BR.data, data)

            val linearLayout = rowItemBinding.linearId
            linearLayout.removeAllViews();

            data.beerData.forEach {
                val textView = TextView(context)
                textView.id = View.generateViewId()
                textView.text = it.beer_brand.plus("  ").plus(it.quantity.toString())
                linearLayout.addView(textView)
            }
            rowItemBinding.executePendingBindings()
            rowItemBinding.cardView.setOnClickListener {
                onItemClick?.invoke(data.vn_detail.vn_name)
            }
        }
    }

    inner class ItemCoffeeViewHolder(
        private var rowItemBinding: RowItemCoffeeBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(rowItemBinding.root) {
        fun bindItem(data: ItemCoffee, position: Int) {
            rowItemBinding.setVariable(BR.data, data)

            val linearLayout = rowItemBinding.linearId
            linearLayout.removeAllViews();
            data.coffeeData.forEach {
                val textView = TextView(context)
                textView.id = View.generateViewId()
                textView.text = "Quantity  ".plus(it.litre.toString()).plus("  ").plus("L")
                linearLayout.addView(textView)
            }
            rowItemBinding.executePendingBindings()

            rowItemBinding.cardView.setOnClickListener {
                 onItemClick?.invoke(data.vn_detail.vn_name)
            }
        }
    }
}
