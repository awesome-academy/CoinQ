package com.sunasterisk.coinqapp.data.repository

import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.model.ExchangeDetail
import com.sunasterisk.coinqapp.data.model.ExchangeEntry
import com.sunasterisk.coinqapp.data.source.ExchangeDataSource
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class ExchangeRepository private constructor(
    private val remote: ExchangeDataSource.Remote,
    private val local: ExchangeDataSource.Local
) : ExchangeDataSource.Remote, ExchangeDataSource.Local {

    override fun getExchanges(
        perPage: Int,
        page: Int,
        callBack: OnLoadDataCallBack<List<Exchange>>
    ) = remote.getExchanges(perPage, page, callBack)

    override fun getExchangeDetail(
        exchangeId: String,
        callBack: OnLoadDataCallBack<ExchangeDetail>
    ) = remote.getExchangeDetail(exchangeId, callBack)

    override fun getExchangeChart(
        exchangeId: String,
        days: Int,
        callBack: OnLoadDataCallBack<List<ExchangeEntry>>
    ) = remote.getExchangeChart(exchangeId, days, callBack)

    override fun insertExchange(exchange: Exchange, callBack: OnLoadDataCallBack<Long>) =
        local.insertExchange(exchange, callBack)

    override fun deleteExchange(exchangeId: String, callBack: OnLoadDataCallBack<Boolean>) =
        local.deleteExchange(exchangeId, callBack)

    override fun getExchangesFavorite(callBack: OnLoadDataCallBack<List<Exchange>>) =
        local.getExchangesFavorite(callBack)

    override fun isFavorite(exchangeId: String, callBack: OnLoadDataCallBack<Int>) =
        local.isFavorite(exchangeId, callBack)

    companion object {
        private var instance: ExchangeRepository? = null

        fun getInstance(remote: ExchangeDataSource.Remote, local: ExchangeDataSource.Local) =
            instance ?: ExchangeRepository(remote, local).also { instance = it }
    }
}
