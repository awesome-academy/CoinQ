package com.sunasterisk.coinqapp.ui.coindetail.chart

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.CoinEntry

interface CoinChartContract {
    interface View : BaseView {
        fun showCoinChart(coinEntries: List<CoinEntry>)
    }

    interface Presenter {
        fun getCoinChart(coinId: String)
    }
}
