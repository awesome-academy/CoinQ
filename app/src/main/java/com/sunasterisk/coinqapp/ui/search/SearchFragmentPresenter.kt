package com.sunasterisk.coinqapp.ui.search

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.repository.ExchangeRepository
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_DAYCHANGE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_ORDER
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_PAGE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_PERPAGE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_SPARKLINE
import com.sunasterisk.coinqapp.utils.RequestConstant.REQUEST_VSCURRENCY
import java.util.*

class SearchFragmentPresenter(
    private val view: SearchFragmentContract.View,
    private val coinRepository: CoinRepository,
    private val exchangeRepository: ExchangeRepository
) : SearchFragmentContract.Presenter {

    override fun getCoins() {
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
                view.updateCoins(data)
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
            }
        })
    }

    override fun getExchanges() {
        exchangeRepository.getExchanges(
            REQUEST_PERPAGE,
            REQUEST_PAGE,
            object : OnLoadDataCallBack<List<Exchange>> {
                override fun onSuccess(data: List<Exchange>) {
                    view.updateExchanges(data)
                    view.hideLoading()
                }

                override fun onFailure(error: Exception?) {
                    view.showError(error)
                    view.hideLoading()
                }
            })
    }

    override fun getCoinsByKey(key: String, coins: List<Coin>) {
        val clearKey = key.toLowerCase(Locale.ROOT).trim()
        val coinResults = mutableListOf<Coin>()
        for (coin in coins) {
            if (coin.name.toLowerCase(Locale.ROOT).contains(clearKey))
                coinResults.add(coin)
        }
        view.showCoinResults(coinResults)
    }

    override fun getExchangesByKey(key: String, exchanges: List<Exchange>) {
        val clearKey = key.toLowerCase(Locale.ROOT).trim()
        val exchangeResults = mutableListOf<Exchange>()
        for (exchange in exchanges) {
            if (exchange.name.toLowerCase(Locale.ROOT).contains(clearKey))
                exchangeResults.add(exchange)
        }
        view.showExchangeResults(exchangeResults)
    }
}
