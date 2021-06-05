package com.sunasterisk.coinqapp.data.source.remote

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.model.CoinDetail
import com.sunasterisk.coinqapp.data.model.CoinEntry
import com.sunasterisk.coinqapp.data.source.CoinDataSource
import com.sunasterisk.coinqapp.data.source.remote.api.APIQuery
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoinDetail
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.utils.LoadDataAsyncTask
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import com.sunasterisk.coinqapp.utils.getJsonFromUrl
import com.sunasterisk.coinqapp.utils.parseJsonToObject
import org.json.JSONArray
import org.json.JSONObject

class CoinRemoteDataSource private constructor() : CoinDataSource.Remote {

    override fun getCoins(requestCoins: RequestCoins, callBack: OnLoadDataCallBack<List<Coin>>) {
        LoadDataAsyncTask(callBack) {
            getCoins(requestCoins)
        }.execute()
    }

    override fun getCoin(requestCoins: RequestCoins, callBack: OnLoadDataCallBack<Coin>) {
        LoadDataAsyncTask(callBack) {
            getCoin(requestCoins)[0]
        }.execute()
    }

    override fun getCoinDetail(
        requestCoinDetail: RequestCoinDetail,
        callBack: OnLoadDataCallBack<CoinDetail>
    ) {
        LoadDataAsyncTask(callBack) {
            getCoinDetail(requestCoinDetail)[0]
        }.execute()
    }

    override fun getCoinChart(
        coinId: String,
        moneyExchange: String,
        days: Int,
        callBack: OnLoadDataCallBack<List<CoinEntry>>
    ) {
        LoadDataAsyncTask(callBack) {
            getCoinChart(coinId, moneyExchange, days)
        }.execute()
    }

    private fun getCoins(requestCoins: RequestCoins): List<Coin> {
        val jsonString = getJsonFromUrl(APIQuery.queryCoins(requestCoins))
        val jsonArray = JSONArray(jsonString)
        return jsonArray.parseJsonToObject()
    }

    private fun getCoin(requestCoins: RequestCoins) : List<Coin>{
        val jsonString = getJsonFromUrl(APIQuery.queryCoin(requestCoins))
        val jsonArray = JSONArray(jsonString)
        return jsonArray.parseJsonToObject()
    }

    private fun getCoinDetail(requestCoinDetail: RequestCoinDetail): List<CoinDetail> {
        val jsonString = getJsonFromUrl(APIQuery.queryCoinDetail(requestCoinDetail))
        val jsonArray = JSONArray().put(JSONObject(jsonString))
        return jsonArray.parseJsonToObject()
    }

    private fun getCoinChart(coinId: String, moneyExchange: String, days: Int): List<CoinEntry> {
        val jsonString = getJsonFromUrl(APIQuery.queryCoinChart(coinId, moneyExchange, days))
        val jsonArray = JSONArray(jsonString)
        return jsonArray.parseJsonToObject()
    }

    companion object {
        private var instance: CoinRemoteDataSource? = null

        fun getInstance() = instance ?: CoinRemoteDataSource().also { instance = it }
    }
}
