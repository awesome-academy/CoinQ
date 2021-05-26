package com.sunasterisk.coinqapp.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : BaseViewHolder<T>> : RecyclerView.Adapter<V>() {

    private val items = mutableListOf<T>()

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.onBindData(items[position])
    }

    override fun getItemCount() = items.size

    open fun updateData(newData: MutableList<T>) {
        items.run {
            clear()
            this.addAll(newData)
            notifyDataSetChanged()
        }
    }
}
