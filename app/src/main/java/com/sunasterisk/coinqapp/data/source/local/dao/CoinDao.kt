package com.sunasterisk.coinqapp.data.source.local.dao

import com.sunasterisk.coinqapp.data.model.Coin

interface CoinDao {
    fun insertCoin(coin: Coin): Long
    fun deleteCoin(coinId: String): Boolean
    fun getCoinsFavorite(): List<Coin>
    fun isFavorite(coinId: String): Int
}
