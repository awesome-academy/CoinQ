package com.sunasterisk.coinqapp.ui.listcoin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseAdapter
import com.sunasterisk.coinqapp.base.BaseViewHolder
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.databinding.ItemCoinBinding
import com.sunasterisk.coinqapp.utils.loadImage
import java.text.DecimalFormat

class ListCoinAdapter(private val onItemClick: (Coin) -> Unit) :
    BaseAdapter<Coin, ListCoinAdapter.ListCoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListCoinViewHolder(
            ItemCoinBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    class ListCoinViewHolder(
        private val binding: ItemCoinBinding,
        onItemClick: (Coin) -> Unit
    ) : BaseViewHolder<Coin>(binding, onItemClick) {

        override fun onBindData(itemData: Coin) {
            super.onBindData(itemData)
            with(binding) {
                itemData.apply {
                    textCoinName.text = symbol
                    textDayChange.apply {
                        text = DecimalFormat(PATTEN_TEX_DAY).format(dayChange / 100)
                        if (dayChange >= 0) setTextColor(this.resources.getColor(R.color.green_bot_nav))
                        else setTextColor(this.resources.getColor(R.color.color_pomegranate))
                    }
                    textPrice.text = DecimalFormat(PATTEN_TEXT_PRICE).format(price)
                    textMarketCap.text = DecimalFormat(PATTEN_TEXT_MARKET).format(marketCap)
                    imageCoin.loadImage(image)
                }
            }
        }

        companion object {
            const val PATTEN_TEX_DAY = "#0.00%"
            const val PATTEN_TEXT_PRICE = "$###,###.####"
            const val PATTEN_TEXT_MARKET = "$###,###,##0"
        }
    }
}
