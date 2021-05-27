package com.sunasterisk.coinqapp.ui.listexchange

import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.repository.ExchangeRepository
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_PAGE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_PERPAGE

class ListExchangePresenter(
    private val exchangeRepository: ExchangeRepository,
    private val view: ListExchangeContract.View
) : ListExchangeContract.Presenter {

    override fun getListExchange() {
        view.showLoading()
        exchangeRepository.getExchanges(REQUEST_PERPAGE, REQUEST_PAGE, object : OnLoadDataCallBack<List<Exchange>> {
            override fun onSuccess(data: List<Exchange>) {
                view.showListExchange(data.toMutableList())
                view.hideLoading()
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
                view.hideLoading()
            }
        })
    }

}
