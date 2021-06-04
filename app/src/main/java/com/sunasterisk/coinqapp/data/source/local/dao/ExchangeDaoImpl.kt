package com.sunasterisk.coinqapp.data.source.local.dao

import android.annotation.SuppressLint
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.model.Exchange.Companion.EXCHANGE_TABLE_NAME
import com.sunasterisk.coinqapp.data.model.Exchange.Companion.KEY_ID
import com.sunasterisk.coinqapp.data.source.local.db.AppDataBase

class ExchangeDaoImpl private constructor(dataBase: AppDataBase) : ExchangeDao {

    private val writeableDB = dataBase.writableDatabase
    private val readableDB = dataBase.readableDatabase

    override fun insertExchange(exchange: Exchange) =
        writeableDB.insert(EXCHANGE_TABLE_NAME, null, exchange.getContentValue())

    override fun deleteExchange(exchangeId: String) =
        writeableDB.delete(EXCHANGE_TABLE_NAME, "$KEY_ID=?", arrayOf(exchangeId)) > 0

    override fun getExchangesFavorite(): List<Exchange> {
        val exchanges = mutableListOf<Exchange>()
        val cursor = readableDB.query(
            EXCHANGE_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                exchanges.add(Exchange(it))
            }
        }
        return exchanges
    }

    @SuppressLint("Recycle")
    override fun isFavorite(exchangeId: String): Int {
        val cursor = readableDB.query(
            EXCHANGE_TABLE_NAME,
            null,
            "$KEY_ID = ?",
            arrayOf(exchangeId),
            null,
            null,
            null
        )
        return cursor.count
    }

    companion object {
        private var instance: ExchangeDaoImpl? = null

        fun getInstance(database: AppDataBase): ExchangeDaoImpl =
            instance ?: ExchangeDaoImpl(database).also { instance = it }
    }
}
