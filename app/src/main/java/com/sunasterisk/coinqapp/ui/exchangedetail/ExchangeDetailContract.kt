package com.sunasterisk.coinqapp.ui.exchangedetail

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.model.ExchangeDetail
import com.sunasterisk.coinqapp.data.model.ExchangeEntry

class ExchangeDetailContract {
    interface View : BaseView {
        fun showExchangeDetail(exchangeDetail: ExchangeDetail)
        fun showExchangeChart(exchangeEntries: List<ExchangeEntry>)
        fun showExchangeFavorite(isFavorite: Int)
        fun isInsertedExchangeFavorite(long: Long)
        fun isDeletedExchangeFavorite(boolean: Boolean)
    }

    interface Presenter {
        fun getExchangeDetail(exchangeId: String)
        fun getExchangeChart(exchangeId: String, days: Int)
        fun getExchangeFavorite(exchangeId: String)
        fun insertExchangeFavorite(exchange: Exchange)
        fun deleteExchangeFavorite(exchangeId: String)
    }
}
