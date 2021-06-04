package com.sunasterisk.coinqapp.data.source.local.dao

import com.sunasterisk.coinqapp.data.model.Exchange

interface ExchangeDao {
    fun insertExchange(exchange: Exchange): Long
    fun deleteExchange(exchangeId: String): Boolean
    fun getExchangesFavorite(): List<Exchange>
    fun isFavorite(exchangeId: String): Int
}
