package com.sunasterisk.coinqapp.data.source

import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

interface ExchangeDataSource {
    interface Remote{
        fun getExchanges(perPage: Int, page: Int, callBack: OnLoadDataCallBack<List<Exchange>>)
    }
}
