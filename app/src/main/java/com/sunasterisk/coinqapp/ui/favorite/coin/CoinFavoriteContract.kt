package com.sunasterisk.coinqapp.ui.favorite.coin

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.Coin

interface CoinFavoriteContract {
    interface View : BaseView {
        fun showCoinsFavorite(coins: List<Coin>)
        fun showCoin(coin: Coin)
    }

    interface Presenter {
        fun getCoinsFavorite()
        fun getCoin(coinId: String)
    }
}
