package com.sunasterisk.coinqapp.ui.favorite.exchange.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.coinqapp.base.BaseAdapter
import com.sunasterisk.coinqapp.base.BaseViewHolder
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.databinding.ItemExchangeBinding
import com.sunasterisk.coinqapp.ui.listexchange.adapter.ListExchangeAdapter.ListExchangeViewHolder.Companion.PATTEN_TEXT_VOLUME
import com.sunasterisk.coinqapp.utils.loadImage
import java.text.DecimalFormat

class ExchangeFavoriteAdapter(private val onItemClick: (Exchange) -> Unit) :
    BaseAdapter<Exchange, ExchangeFavoriteAdapter.ExchangeFavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ExchangeFavoriteViewHolder(
            ItemExchangeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    class ExchangeFavoriteViewHolder(
        private val binding: ItemExchangeBinding,
        onItemClick: (Exchange) -> Unit
    ) : BaseViewHolder<Exchange>(binding, onItemClick) {

        override fun onBindData(itemData: Exchange) {
            super.onBindData(itemData)
            with(binding) {
                itemData.apply {
                    imageExchange.loadImage(image)
                    textExchangeName.text = name
                    textRating.text = trustScore.toString()
                    textVolume.text = DecimalFormat(PATTEN_TEXT_VOLUME).format(volume)
                    textVolumeDetail.text = DecimalFormat(PATTEN_TEXT_VOLUME).format(volume)
                }
            }
        }
    }
}
