package com.sunasterisk.coinqapp.data.repository

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.model.CoinDetail
import com.sunasterisk.coinqapp.data.model.CoinEntry
import com.sunasterisk.coinqapp.data.source.CoinDataSource
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoinDetail
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class CoinRepository private constructor(
    private val remote: CoinDataSource.Remote,
    private val local: CoinDataSource.Local
) : CoinDataSource.Remote, CoinDataSource.Local {

    override fun getCoins(requestCoins: RequestCoins, callBack: OnLoadDataCallBack<List<Coin>>) =
        remote.getCoins(requestCoins, callBack)

    override fun getCoin(requestCoins: RequestCoins, callBack: OnLoadDataCallBack<Coin>) {
        remote.getCoin(requestCoins, callBack)
    }

    override fun getCoinDetail(
        requestCoinDetail: RequestCoinDetail,
        callBack: OnLoadDataCallBack<CoinDetail>
    ) {
        remote.getCoinDetail(requestCoinDetail, callBack)
    }

    override fun getCoinChart(
        coinId: String,
        moneyExchange: String,
        days: Int,
        callBack: OnLoadDataCallBack<List<CoinEntry>>
    ) {
        remote.getCoinChart(coinId, moneyExchange, days, callBack)
    }

    override fun insertCoin(coin: Coin, callBack: OnLoadDataCallBack<Long>) =
        local.insertCoin(coin, callBack)

    override fun deleteCoin(coinId: String, callBack: OnLoadDataCallBack<Boolean>) =
        local.deleteCoin(coinId, callBack)

    override fun getCoinsFavorite(callBack: OnLoadDataCallBack<List<Coin>>) =
        local.getCoinsFavorite(callBack)

    override fun isFavorite(coinId: String, callBack: OnLoadDataCallBack<Int>) =
        local.isFavorite(coinId, callBack)

    companion object {
        private var instance: CoinRepository? = null

        fun getInstance(remote: CoinDataSource.Remote, local: CoinDataSource.Local) =
            instance ?: CoinRepository(remote, local).also { instance = it }
    }
}
