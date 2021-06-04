package com.sunasterisk.coinqapp.ui.exchangedetail

import com.sunasterisk.coinqapp.data.model.Exchange
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

    override fun getExchangeFavorite(exchangeId: String) {
        exchangeRepository.isFavorite(exchangeId, object : OnLoadDataCallBack<Int> {
            override fun onSuccess(data: Int) {
                view.showExchangeFavorite(data)
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
            }
        })
    }

    override fun insertExchangeFavorite(exchange: Exchange) {
        exchangeRepository.insertExchange(exchange, object : OnLoadDataCallBack<Long> {
            override fun onSuccess(data: Long) {
                if (data > 0) view.isInsertedExchangeFavorite(data)
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
            }
        })
    }

    override fun deleteExchangeFavorite(exchangeId: String) {
        exchangeRepository.deleteExchange(exchangeId, object : OnLoadDataCallBack<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.isDeletedExchangeFavorite(data)
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
            }
        })
    }
}
