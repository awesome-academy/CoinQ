package com.sunasterisk.coinqapp.ui.coindetail

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.Coin

class CoinDetailContract {
    interface View : BaseView {
        fun showCoinFavorite(isFavorite: Int)
        fun isInsertedCoinFavorite(long: Long)
        fun isDeletedCoinFavorite(boolean: Boolean)
    }

    interface Presenter {
        fun getCoinFavorite(coinId: String)
        fun insertCoinFavorite(coin: Coin)
        fun deleteCoinFavorite(coinId: String)
    }
}
