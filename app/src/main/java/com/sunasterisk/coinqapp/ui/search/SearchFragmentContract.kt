package com.sunasterisk.coinqapp.ui.search

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.model.Exchange

class SearchFragmentContract {
    interface View : BaseView {
        fun updateCoins(coins: List<Coin>)
        fun showCoinResults(coins : MutableList<Coin>)
        fun updateExchanges(exchanges: List<Exchange>)
        fun showExchangeResults(exchanges : MutableList<Exchange>)
    }

    interface Presenter {
        fun getCoins()
        fun getExchanges()
        fun getCoinsByKey(key: String, coins: List<Coin>)
        fun getExchangesByKey(key: String, exchanges: List<Exchange>)
    }
}
