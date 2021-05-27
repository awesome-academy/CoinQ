package com.sunasterisk.coinqapp.ui.listexchange

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.Exchange

interface ListExchangeContract {
    interface View : BaseView{
        fun showListExchange(exchanges : List<Exchange>)
    }
    interface Presenter{
        fun getListExchange()
    }
}
