package com.sunasterisk.coinqapp.ui.favorite.exchange

import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.repository.ExchangeRepository
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class ExchangeFavoritePresenter(
    private val view: ExchangeFavoriteContract.View,
    private val exchangeRepository: ExchangeRepository
) : ExchangeFavoriteContract.Presenter {

    override fun getExchangesFavorite() {
        view.showLoading()
        exchangeRepository.getExchangesFavorite(object : OnLoadDataCallBack<List<Exchange>> {
            override fun onSuccess(data: List<Exchange>) {
                view.showExchangesFavorite(data)
                view.hideLoading()
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
                view.hideLoading()
            }
        })
    }
}
