package com.sunasterisk.coinqapp.data.repository

import com.sunasterisk.coinqapp.data.model.Exchange
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

    companion object {
        private var instance: ExchangeRepository? = null
        fun getInstance(remote: ExchangeDataSource.Remote) =
            instance ?: ExchangeRepository(remote).also { instance = it }
    }
}
