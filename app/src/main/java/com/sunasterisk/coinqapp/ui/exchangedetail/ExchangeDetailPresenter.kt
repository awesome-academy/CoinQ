package com.sunasterisk.coinqapp.ui.exchangedetail

import com.sunasterisk.coinqapp.data.model.ExchangeDetail
import com.sunasterisk.coinqapp.data.model.ExchangeEntry
import com.sunasterisk.coinqapp.data.repository.ExchangeRepository
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class ExchangeDetailPresenter(
    private val view: ExchangeDetailContract.View,
    private val exchangeRepository: ExchangeRepository
) : ExchangeDetailContract.Presenter {

    override fun getExchangeDetail(exchangeId: String) {
        view.showLoading()
        exchangeRepository.getExchangeDetail(
            exchangeId,
            object : OnLoadDataCallBack<ExchangeDetail> {
                override fun onSuccess(data: ExchangeDetail) {
                    view.showExchangeDetail(data)
                    view.hideLoading()
                }

                override fun onFailure(error: Exception?) {
                    view.showError(error)
                    view.hideLoading()
                }
            })
    }

    override fun getExchangeChart(exchangeId: String, days: Int) {
        exchangeRepository.getExchangeChart(
            exchangeId,
            days,
            object : OnLoadDataCallBack<List<ExchangeEntry>> {
                override fun onSuccess(data: List<ExchangeEntry>) {
                    view.showExchangeChart(data)
                }

                override fun onFailure(error: Exception?) {
                    view.showError(error)
                    error?.printStackTrace()
                }
            })
    }
}
