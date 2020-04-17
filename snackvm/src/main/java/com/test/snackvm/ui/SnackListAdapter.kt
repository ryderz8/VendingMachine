package com.test.snackvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.repository.entity.SnacksVM
import com.test.snackvm.R
import com.test.snackvm.BR
import com.test.snackvm.databinding.SnackRowItemBinding

class SnackListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    RecyclerBindingContract<List<SnacksVM>> {

    var itemList = listOf<SnacksVM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.snack_row_item, parent, false
            )
        )
    }

    override fun setData(data: List<SnacksVM>) {
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

    inner class ItemViewHolder(private var rowItemBinding: SnackRowItemBinding) :
        RecyclerView.ViewHolder(rowItemBinding.root) {
        fun bindItem(data: SnacksVM, position: Int) {
            rowItemBinding.setVariable(BR.data, data)
            rowItemBinding.executePendingBindings()

        }
    }
}
