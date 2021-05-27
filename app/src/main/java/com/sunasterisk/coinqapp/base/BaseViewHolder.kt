package com.sunasterisk.coinqapp.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(binding: ViewBinding, onItemClick: (T) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    private var itemData: T? = null

    init {
        itemView.setOnClickListener {
            itemData?.let(onItemClick)
        }
    }

    open fun onBindData(itemData : T){
        this.itemData = itemData
    }
}
