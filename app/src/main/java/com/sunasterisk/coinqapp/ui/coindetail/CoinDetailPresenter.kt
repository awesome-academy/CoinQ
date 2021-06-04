package com.sunasterisk.coinqapp.ui.coindetail

import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import java.lang.Exception

class CoinDetailPresenter(
    private val view : CoinDetailContract.View,
    private val coinRepository: CoinRepository
) : CoinDetailContract.Presenter {

    override fun getCoinFavorite(coinId: String) {
        coinRepository.isFavorite(coinId, object : OnLoadDataCallBack<Int>{
            override fun onSuccess(data: Int) {
                view.showCoinFavorite(data)
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
            }

        })
    }

    override fun insertCoinFavorite(coin: Coin) {
        coinRepository.insertCoin(coin, object : OnLoadDataCallBack<Long>{
            override fun onSuccess(data: Long) {
                if(data > 0) view.isInsertedCoinFavorite(data)
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
            }
        })
    }

    override fun deleteCoinFavorite(coinId: String) {
        coinRepository.deleteCoin(coinId, object : OnLoadDataCallBack<Boolean>{
            override fun onSuccess(data: Boolean) {
                view.isDeletedCoinFavorite(data)
            }

            override fun onFailure(error: Exception?) {
                view.showError(error)
            }
        })
    }
}
