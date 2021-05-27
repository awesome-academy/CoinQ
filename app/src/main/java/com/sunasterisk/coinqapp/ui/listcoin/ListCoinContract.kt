package com.sunasterisk.coinqapp.ui.listcoin

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.Coin

interface ListCoinContract {
    interface View : BaseView{
        fun showListCoin(coins : List<Coin>)
    }
    interface Presenter{
        fun getListCoin()
    }
}
