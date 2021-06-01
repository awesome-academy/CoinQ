package com.sunasterisk.coinqapp.ui.coindetail.chart

interface CoinChartContract {
    interface View {
        fun showCoinChart()
    }

    interface Presenter {
        fun getCoinChart(coinId: String, moneyExchange: String, days: Int)
    }
}
