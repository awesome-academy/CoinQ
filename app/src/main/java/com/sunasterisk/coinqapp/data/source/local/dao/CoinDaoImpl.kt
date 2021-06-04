package com.sunasterisk.coinqapp.data.source.local.dao

import android.annotation.SuppressLint
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.model.Coin.Companion.COIN_KEY_ID
import com.sunasterisk.coinqapp.data.model.Coin.Companion.COIN_TABLE_NAME
import com.sunasterisk.coinqapp.data.source.local.db.AppDataBase

class CoinDaoImpl private constructor(dataBase: AppDataBase) : CoinDao {

    private val writeableDB = dataBase.writableDatabase
    private val readableDB = dataBase.readableDatabase

    override fun insertCoin(coin: Coin) =
        writeableDB.insert(COIN_TABLE_NAME, null, coin.getContentValue())

    override fun deleteCoin(coinId: String) =
        writeableDB.delete(COIN_TABLE_NAME, "$COIN_KEY_ID=?", arrayOf(coinId)) > 0

    override fun getCoinsFavorite(): List<Coin> {
        val coins = mutableListOf<Coin>()
        val cursor = readableDB.query(
            COIN_TABLE_NAME,
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
                coins.add(Coin(it))
            }
        }
        return coins
    }

    @SuppressLint("Recycle")
    override fun isFavorite(coinId: String): Int {
        val cursor = readableDB.query(
            COIN_TABLE_NAME,
            null,
            "$COIN_KEY_ID = ?",
            arrayOf(coinId),
            null,
            null,
            null
        )
        return cursor.count
    }

    companion object {
        private var instance: CoinDaoImpl? = null

        fun getInstance(database: AppDataBase): CoinDaoImpl =
            instance ?: CoinDaoImpl(database).also { instance = it }
    }
}
