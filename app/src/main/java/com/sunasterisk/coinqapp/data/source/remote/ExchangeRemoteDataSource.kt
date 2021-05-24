package com.sunasterisk.coinqapp.data.source.remote

import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.source.ExchangeDataSource
import com.sunasterisk.coinqapp.data.source.remote.api.APIQuery
import com.sunasterisk.coinqapp.utils.LoadDataAsyncTask
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import com.sunasterisk.coinqapp.utils.getJsonFromUrl
import com.sunasterisk.coinqapp.utils.parseJsonToObject
import org.json.JSONArray

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

    private fun getExchanges(perPage: Int, page: Int): List<Exchange> {
        val jsonString = getJsonFromUrl(APIQuery.queryExchanges(perPage, page))
        return JSONArray(jsonString).parseJsonToObject()
    }

    companion object {
        private var instance: ExchangeRemoteDataSource? = null

        fun getInstance() = instance ?: ExchangeRemoteDataSource().also { instance = it }
    }
}
