package com.sunasterisk.coinqapp.ui.favorite.coin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.coinqapp.base.BaseAdapter
import com.sunasterisk.coinqapp.base.BaseViewHolder
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.databinding.ItemFavoriteBinding
import com.sunasterisk.coinqapp.utils.loadImage

class CoinFavoriteAdapter(private val onItemClick: (Coin) -> Unit) :
    BaseAdapter<Coin, CoinFavoriteAdapter.CoinFavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CoinFavoriteViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    class CoinFavoriteViewHolder(
        private val binding: ItemFavoriteBinding,
        onItemClick: (Coin) -> Unit
    ) : BaseViewHolder<Coin>(binding, onItemClick) {

        override fun onBindData(itemData: Coin) {
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
