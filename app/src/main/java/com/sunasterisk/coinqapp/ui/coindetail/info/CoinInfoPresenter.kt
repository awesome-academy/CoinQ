package com.sunasterisk.coinqapp.ui.coindetail.info

import com.sunasterisk.coinqapp.data.model.CoinDetail
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoinDetail
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class CoinInfoPresenter(
    private val view: CoinInfoContract.View,
    private val coinRepository: CoinRepository
) : CoinInfoContract.Presenter {

    override fun getCoinDetail(coinId: String) {
        view.showLoading()
        val requestCoinDetail = RequestCoinDetail(
            coinId,
            MULTI_LANGUAGE,
            TICKER,
            MARKET,
            COMMUNITY,
            DEV_DATA
        )
        coinRepository.getCoinDetail(requestCoinDetail, object : OnLoadDataCallBack<CoinDetail> {
            override fun onSuccess(data: CoinDetail) {
                view.showCoinDetail(data)
                view.hideLoading()
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
                view.hideLoading()
            }
        })
    }

    companion object {
        private const val MULTI_LANGUAGE = false
        private const val TICKER = false
        private const val MARKET = false
        private const val COMMUNITY = false
        private const val DEV_DATA = false
    }
}
