package com.sunasterisk.coinqapp.ui.coindetail.chart

import com.sunasterisk.coinqapp.data.repository.CoinRepository

class CoinChartPresenter(
    private val coinRepository: CoinRepository,
    private val view : CoinChartContract.View,
) : CoinChartContract.Presenter{

    override fun getCoinChart(coinId: String, moneyExchange: String, days: Int) {
    }
}
