package com.sunasterisk.coinqapp.data.source

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.model.CoinDetail
import com.sunasterisk.coinqapp.data.model.CoinEntry
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoinDetail
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

interface CoinDataSource {

    interface Remote {
        fun getCoins(requestCoins: RequestCoins, callBack: OnLoadDataCallBack<List<Coin>>)
        fun getCoinDetail(requestCoinDetail: RequestCoinDetail, callBack: OnLoadDataCallBack<CoinDetail>)
        fun getCoinChart(coinId: String, moneyExchange: String, days: Int, callBack: OnLoadDataCallBack<List<CoinEntry>>)
    }
}
