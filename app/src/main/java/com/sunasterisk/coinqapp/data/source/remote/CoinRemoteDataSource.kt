package com.sunasterisk.coinqapp.data.source.remote

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.source.CoinDataSource
import com.sunasterisk.coinqapp.data.source.remote.api.APIQuery
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.utils.LoadDataAsyncTask
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import com.sunasterisk.coinqapp.utils.getJsonFromUrl
import com.sunasterisk.coinqapp.utils.parseJsonToObject
import org.json.JSONArray

class CoinRemoteDataSource private constructor() : CoinDataSource.Remote {

    override fun getCoins(requestCoins: RequestCoins, callBack: OnLoadDataCallBack<List<Coin>>) {
        LoadDataAsyncTask(callBack) {
            getCoins(requestCoins)
        }.execute()
    }

    private fun getCoins(requestCoins: RequestCoins): List<Coin> {
        val jsonString = getJsonFromUrl(APIQuery.queryCoins(requestCoins))
        val jsonArray = JSONArray(jsonString)
        return jsonArray.parseJsonToObject()
    }

    companion object {
        private var instance: CoinRemoteDataSource? = null

        fun getInstance() = instance ?: CoinRemoteDataSource().also { instance = it }
    }
}
