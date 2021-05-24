package com.sunasterisk.coinqapp.data.source

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoinDetail
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

interface CoinDataSource {

    interface Remote {
        fun getCoins(requestCoins: RequestCoins, callBack: OnLoadDataCallBack<List<Coin>>)
    }
}
