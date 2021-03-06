package com.sunasterisk.coinqapp.ui.listcoin

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_DAYCHANGE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_ORDER
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_PAGE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_PERPAGE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_SPARKLINE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_VSCURRENCY

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
}
