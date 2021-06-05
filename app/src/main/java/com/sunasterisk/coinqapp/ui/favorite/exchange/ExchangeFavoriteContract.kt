package com.sunasterisk.coinqapp.ui.favorite.exchange

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.Exchange

class ExchangeFavoriteContract {
    interface View : BaseView {
        fun showExchangesFavorite(exchanges : List<Exchange>)
    }

    interface Presenter{
        fun getExchangesFavorite()
    }
}
