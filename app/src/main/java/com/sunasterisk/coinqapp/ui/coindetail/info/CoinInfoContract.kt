package com.sunasterisk.coinqapp.ui.coindetail.info

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.CoinDetail

class CoinInfoContract {
    interface View : BaseView {
        fun showCoinDetail(coinDetail: CoinDetail)
    }

    interface Presenter {
        fun getCoinDetail(coinId: String)
    }
}
