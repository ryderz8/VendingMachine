package com.test.vendingmachine.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.repository.entity.VendingMachineEntity
import com.test.vendingmachine.R
import com.test.vendingmachine.BR
import com.test.vendingmachine.databinding.RowItemBinding

class ListItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    RecyclerBindingContract<List<VendingMachineEntity>> {

    var itemList = listOf<VendingMachineEntity>()

    var onItemClick: ((VendingMachineEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_item, parent, false
            )
        )
    }

    override fun setData(data: List<VendingMachineEntity>) {
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

    inner class ItemViewHolder(private var rowItemBinding: RowItemBinding) :
        RecyclerView.ViewHolder(rowItemBinding.root) {
        fun bindItem(data: VendingMachineEntity, position: Int) {
            rowItemBinding.setVariable(BR.data, data)
            rowItemBinding.executePendingBindings()

            rowItemBinding.cardView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }
}
