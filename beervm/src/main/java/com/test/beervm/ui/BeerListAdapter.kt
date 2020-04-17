package com.test.beervm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.beervm.R
import com.test.beervm.BR
import com.test.beervm.databinding.BeerRowItemBinding
import com.test.repository.entity.BeerVM

class BeerListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    RecyclerBindingContract<List<BeerVM>> {

    var itemList = listOf<BeerVM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.beer_row_item, parent, false
            )
        )
    }

    override fun setData(data: List<BeerVM>) {
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

    inner class ItemViewHolder(private var rowItemBinding: BeerRowItemBinding) :
        RecyclerView.ViewHolder(rowItemBinding.root) {
        fun bindItem(data: BeerVM, position: Int) {
            rowItemBinding.setVariable(BR.data, data)
            rowItemBinding.executePendingBindings()

        }
    }
}
