package com.sunasterisk.coinqapp.ui.listcoin

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_PAGE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_PERPAGE

class ListCoinPresenter(
    private val view: ListCoinContract.View,
    private val coinRepository: CoinRepository
) : ListCoinContract.Presenter {

    override fun getListCoin() {
        view.showLoading()
        val requestCoins = RequestCoins(
            REQUEST_VSCURRENCY,
            REQUEST_ORDER,
            REQUEST_PERPAGE,
            REQUEST_PAGE,
            REQUEST_SPARKLINE,
            REQUEST_DAYCHANGE
        )
        coinRepository.getCoins(requestCoins, object : OnLoadDataCallBack<List<Coin>> {
            override fun onSuccess(data: List<Coin>) {
                view.showListCoin(data.toMutableList())
                view.hideLoading()
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
                view.hideLoading()
            }
        })
    }

    companion object {
        const val REQUEST_VSCURRENCY = "usd"
        const val REQUEST_ORDER = "market_cap_desc"
        const val REQUEST_SPARKLINE = true
        const val REQUEST_DAYCHANGE = "24h"
    }
}
