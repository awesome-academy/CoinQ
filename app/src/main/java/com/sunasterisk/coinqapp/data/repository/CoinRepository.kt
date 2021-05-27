package com.sunasterisk.coinqapp.data.repository

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.source.CoinDataSource
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class CoinRepository private constructor(
    private val remote: CoinDataSource.Remote
) : CoinDataSource.Remote {

    override fun getCoins(requestCoins: RequestCoins, callBack: OnLoadDataCallBack<List<Coin>>) =
        remote.getCoins(requestCoins, callBack)

    companion object {
        private var instance: CoinRepository? = null
        fun getInstance(remote: CoinDataSource.Remote) =
            instance ?: CoinRepository(remote).also { instance = it }
    }
}
