package com.sunasterisk.coinqapp.data.source.local

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.source.CoinDataSource
import com.sunasterisk.coinqapp.data.source.local.dao.CoinDao
import com.sunasterisk.coinqapp.utils.LoadDataAsyncTask
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class CoinLocalDataSource private constructor(
    private val coinDao: CoinDao
) : CoinDataSource.Local {

    override fun insertCoin(coin: Coin, callBack: OnLoadDataCallBack<Long>) {
        LoadDataAsyncTask(callBack) {
            coinDao.insertCoin(coin)
        }.execute()
    }

    override fun deleteCoin(coinId: String, callBack: OnLoadDataCallBack<Boolean>) {
        LoadDataAsyncTask(callBack) {
            coinDao.deleteCoin(coinId)
        }.execute()
    }

    override fun getCoinsFavorite(callBack: OnLoadDataCallBack<List<Coin>>) {
        LoadDataAsyncTask(callBack) {
            coinDao.getCoinsFavorite()
        }.execute()
    }

    override fun isFavorite(coinId: String, callBack: OnLoadDataCallBack<Int>) {
        LoadDataAsyncTask(callBack) {
            coinDao.isFavorite(coinId)
        }.execute()
    }

    companion object {
        private var instance: CoinLocalDataSource? = null

        fun getInstance(coinDao: CoinDao) =
            instance ?: CoinLocalDataSource(coinDao).also { instance = it }
    }
}
