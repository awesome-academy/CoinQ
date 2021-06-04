package com.sunasterisk.coinqapp.data.source

import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.model.ExchangeDetail
import com.sunasterisk.coinqapp.data.model.ExchangeEntry
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

interface ExchangeDataSource {
    interface Remote{
        fun getExchanges(perPage: Int, page: Int, callBack: OnLoadDataCallBack<List<Exchange>>)
        fun getExchangeDetail(exchangeId : String, callBack: OnLoadDataCallBack<ExchangeDetail>)
        fun getExchangeChart(exchangeId: String, days : Int, callBack: OnLoadDataCallBack<List<ExchangeEntry>>)
    }

    interface Local{
        fun insertExchange(exchange: Exchange, callBack: OnLoadDataCallBack<Long>)
        fun deleteExchange(exchangeId: String, callBack: OnLoadDataCallBack<Boolean>)
        fun getExchangesFavorite(callBack: OnLoadDataCallBack<List<Exchange>>)
        fun isFavorite(exchangeId: String, callBack: OnLoadDataCallBack<Int>)
    }
}
