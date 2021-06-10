package com.sunasterisk.coinqapp.ui.calculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.coinqapp.base.BaseAdapter
import com.sunasterisk.coinqapp.base.BaseViewHolder
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.databinding.ItemCalculatorBinding
import com.sunasterisk.coinqapp.utils.Pattern.PATTERN_TEXT_NUMBER
import com.sunasterisk.coinqapp.utils.Pattern.formatToPattern
import com.sunasterisk.coinqapp.utils.loadImage

class CalculatorAdapter(
    private val onItemClick: (Coin) -> Unit
) :
    BaseAdapter<Coin, CalculatorAdapter.CalculatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CalculatorViewHolder(
            ItemCalculatorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )

    fun updateNewData(newData: MutableList<Coin>, numberUSD: Double) {
        val data = newData.mapIndexed { _, coin ->
            coin.copy(price = numberUSD/(coin.price))
        }
        updateData(data.toMutableList())
    }

    class CalculatorViewHolder(
        private val binding: ItemCalculatorBinding,
        onItemClick: (Coin) -> Unit,
    ) : BaseViewHolder<Coin>(binding, onItemClick) {

        override fun onBindData(itemData: Coin) {
            super.onBindData(itemData)
            with(binding) {
                itemData.apply {
                    imageCoin.loadImage(image)
                    textCoinSymbol.text = symbol
                    textNumberCoins.text = formatToPattern(PATTERN_TEXT_NUMBER, price)
                }
            }
        }
    }
}
