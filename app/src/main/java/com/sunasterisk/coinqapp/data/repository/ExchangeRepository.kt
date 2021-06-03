package com.sunasterisk.coinqapp.data.repository

import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.model.ExchangeDetail
import com.sunasterisk.coinqapp.data.model.ExchangeEntry
import com.sunasterisk.coinqapp.data.source.ExchangeDataSource
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class ExchangeRepository private constructor(
    private val remote: ExchangeDataSource.Remote
) : ExchangeDataSource.Remote {

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

    companion object {
        private var instance: ExchangeRepository? = null

        fun getInstance(remote: ExchangeDataSource.Remote) =
            instance ?: ExchangeRepository(remote).also { instance = it }
    }
}
