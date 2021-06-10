package com.sunasterisk.coinqapp.ui.calculator

import com.sunasterisk.coinqapp.base.BaseView
import com.sunasterisk.coinqapp.data.model.Coin

class CalculatorContract {
    interface View : BaseView {
        fun showCoins(coins: List<Coin>)
    }

    interface Presenter {
        fun getCoins()
    }
}
