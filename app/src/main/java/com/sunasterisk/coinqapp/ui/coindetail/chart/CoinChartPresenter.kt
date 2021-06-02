package com.sunasterisk.coinqapp.ui.coindetail.chart

import com.sunasterisk.coinqapp.data.model.CoinEntry
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.ui.listcoin.ListCoinPresenter
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class CoinChartPresenter(
    private val coinRepository: CoinRepository,
    private val view: CoinChartContract.View,
) : CoinChartContract.Presenter {

    override fun getCoinChart(coinId: String) {
        view.showLoading()
        coinRepository.getCoinChart(
            coinId,
            ListCoinPresenter.REQUEST_VSCURRENCY,
            REQUEST_DAYS,
            object : OnLoadDataCallBack<List<CoinEntry>> {
                override fun onSuccess(data: List<CoinEntry>) {
                    view.showCoinChart(data)
                    view.hideLoading()
                }

                override fun onFailure(error: Exception?) {
                    view.showError(error)
                    view.hideLoading()
                }
            })
    }

    companion object {
        private const val REQUEST_DAYS = 30
    }
}
