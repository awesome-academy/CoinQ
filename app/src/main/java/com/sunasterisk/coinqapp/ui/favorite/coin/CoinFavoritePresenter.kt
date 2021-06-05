package com.sunasterisk.coinqapp.ui.favorite.coin

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

class CoinFavoritePresenter(
    private val view: CoinFavoriteContract.View,
    private val coinRepository: CoinRepository
) : CoinFavoriteContract.Presenter {

    override fun getCoinsFavorite() {
        view.showLoading()
        coinRepository.getCoinsFavorite(object : OnLoadDataCallBack<List<Coin>> {
            override fun onSuccess(data: List<Coin>) {
                view.showCoinsFavorite(data)
                view.hideLoading()
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
                view.hideLoading()
            }
        })
    }

    override fun getCoin(coinId: String) {
        val requestCoins = RequestCoins(
            REQUEST_VSCURRENCY,
            REQUEST_ORDER,
            REQUEST_PERPAGE,
            REQUEST_PAGE,
            REQUEST_SPARKLINE,
            REQUEST_DAYCHANGE,
            coinId
        )
        coinRepository.getCoin(requestCoins, object : OnLoadDataCallBack<Coin> {
            override fun onSuccess(data: Coin) {
                view.showCoin(data)
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
            }
        })
    }
}
