package com.sunasterisk.coinqapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.coinqapp.base.BaseAdapter
import com.sunasterisk.coinqapp.base.BaseViewHolder
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.databinding.ItemFavoriteBinding
import com.sunasterisk.coinqapp.utils.loadImage

class ExchangeSearchAdapter(private val onItemClick: (Exchange) -> Unit) :
    BaseAdapter<Exchange, ExchangeSearchAdapter.ExchangeSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ExchangeSearchViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    class ExchangeSearchViewHolder(
        private val binding: ItemFavoriteBinding,
        onItemClick: (Exchange) -> Unit
    ) : BaseViewHolder<Exchange>(binding, onItemClick) {

        override fun onBindData(itemData: Exchange) {
            super.onBindData(itemData)
            with(binding) {
                itemData.apply {
                    imageCoin.loadImage(image)
                    textName.text = name
                }
            }
        }
    }
}
