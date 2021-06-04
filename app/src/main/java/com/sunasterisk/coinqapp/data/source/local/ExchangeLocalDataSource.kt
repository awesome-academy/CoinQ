package com.sunasterisk.coinqapp.data.source.local

import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.source.ExchangeDataSource
import com.sunasterisk.coinqapp.data.source.local.dao.ExchangeDao
import com.sunasterisk.coinqapp.utils.LoadDataAsyncTask
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack

class ExchangeLocalDataSource private constructor(
    private val exchangeDao: ExchangeDao
) : ExchangeDataSource.Local {

    override fun insertExchange(exchange: Exchange, callBack: OnLoadDataCallBack<Long>) {
        LoadDataAsyncTask(callBack) {
            exchangeDao.insertExchange(exchange)
        }.execute()
    }

    override fun deleteExchange(exchangeId: String, callBack: OnLoadDataCallBack<Boolean>) {
        LoadDataAsyncTask(callBack) {
            exchangeDao.deleteExchange(exchangeId)
        }.execute()
    }

    override fun getExchangesFavorite(callBack: OnLoadDataCallBack<List<Exchange>>) {
        LoadDataAsyncTask(callBack) {
            exchangeDao.getExchangesFavorite()
        }.execute()
    }

    override fun isFavorite(exchangeId: String, callBack: OnLoadDataCallBack<Int>) {
        LoadDataAsyncTask(callBack) {
            exchangeDao.isFavorite(exchangeId)
        }.execute()
    }

    companion object {
        private var instance: ExchangeLocalDataSource? = null

        fun getInstance(exchangeDao: ExchangeDao) =
            instance ?: ExchangeLocalDataSource(exchangeDao).also { instance = it }
    }
}
