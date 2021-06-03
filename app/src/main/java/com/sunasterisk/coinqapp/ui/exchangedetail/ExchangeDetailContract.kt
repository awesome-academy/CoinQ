package com.sunasterisk.coinqapp.ui.exchangedetail

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.ExchangeDetail
import com.sunasterisk.coinqapp.data.model.ExchangeEntry

class ExchangeDetailContract {
    interface View :BaseView{
        fun showExchangeDetail(exchangeDetail : ExchangeDetail)
        fun showExchangeChart(exchangeEntries: List<ExchangeEntry>)
    }
    interface Presenter{
        fun getExchangeDetail(exchangeId : String)
        fun getExchangeChart(exchangeId : String, days : Int)
    }
}
