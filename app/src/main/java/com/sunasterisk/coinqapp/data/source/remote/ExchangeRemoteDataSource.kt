package com.sunasterisk.coinqapp.data.source.remote

import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.model.ExchangeDetail
import com.sunasterisk.coinqapp.data.model.ExchangeEntry
import com.sunasterisk.coinqapp.data.source.ExchangeDataSource
import com.sunasterisk.coinqapp.data.source.remote.api.APIQuery
import com.sunasterisk.coinqapp.utils.LoadDataAsyncTask
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import com.sunasterisk.coinqapp.utils.getJsonFromUrl
import com.sunasterisk.coinqapp.utils.parseJsonToObject
import org.json.JSONArray
import org.json.JSONObject

class ExchangeRemoteDataSource private constructor() : ExchangeDataSource.Remote {

    override fun getExchanges(
        perPage: Int,
        page: Int,
        callBack: OnLoadDataCallBack<List<Exchange>>
    ) {
        LoadDataAsyncTask(callBack) {
            getExchanges(perPage, page)
        }.execute()
    }

    override fun getExchangeDetail(exchangeId: String, callBack: OnLoadDataCallBack<ExchangeDetail>) {
        LoadDataAsyncTask(callBack){
            getExchangeDetail(exchangeId)[0]
        }.execute()
    }

    override fun getExchangeChart(
        exchangeId: String,
        days: Int,
        callBack: OnLoadDataCallBack<List<ExchangeEntry>>
    ) {
        LoadDataAsyncTask(callBack) {
            getExchangeChart(exchangeId, days)
        }.execute()
    }

    private fun getExchanges(perPage: Int, page: Int): List<Exchange> {
        val jsonString = getJsonFromUrl(APIQuery.queryExchanges(perPage, page))
        return JSONArray(jsonString).parseJsonToObject()
    }

    private fun getExchangeDetail(exchangeId : String) : List<ExchangeDetail>{
        val jsonString = getJsonFromUrl(APIQuery.queryExchangeDetail(exchangeId))
        val jsonArray = JSONArray().put(JSONObject(jsonString))
        return jsonArray.parseJsonToObject()
    }

    private fun getExchangeChart(exchangeId: String, days: Int): List<ExchangeEntry> {
        val jsonString = getJsonFromUrl(APIQuery.queryExchangeChart(exchangeId, days))
        val jsonArray = JSONArray(jsonString)
        return jsonArray.parseJsonToObject()
    }

    companion object {
        private var instance: ExchangeRemoteDataSource? = null

        fun getInstance() = instance ?: ExchangeRemoteDataSource().also { instance = it }
    }
}
