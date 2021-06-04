package com.sunasterisk.coinqapp.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_CURRENT
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_HIGH
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_ID
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_IMAGE
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_LOW
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_MAX
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_MAX_HIGH
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_MAX_LOW
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_NAME
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_PRICE
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_RANK
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_SYMBOL
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_TOTAL
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_VOLUME
import com.sunasterisk.coinqapp.utils.CoinConstant.DAY_CHANGE
import com.sunasterisk.coinqapp.utils.CoinConstant.MARKET_CAP
import com.sunasterisk.coinqapp.utils.Default
import com.sunasterisk.coinqapp.utils.Default.DEFAULT_DOUBLE
import com.sunasterisk.coinqapp.utils.Default.DEFAULT_INT
import com.sunasterisk.coinqapp.utils.Default.DEFAULT_LONG
import com.sunasterisk.coinqapp.utils.Default.DEFAULT_STRING
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val price: Double,
    val dayChange: Double,
    val marketCap: Long,
    val rank: Int,
    val volume: Long,
    val high: Double,
    val low: Double,
    val maxHigh: Double,
    val maxLow: Double,
    val totalSupply: Int,
    val maxSupply : Int,
    val currentSupply : Int
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(COIN_ID),
        jsonObject.getString(COIN_NAME),
        jsonObject.getString(COIN_SYMBOL),
        jsonObject.getString(COIN_IMAGE),
        jsonObject.getDouble(COIN_PRICE),
        jsonObject.getDouble(DAY_CHANGE),
        jsonObject.getLong(MARKET_CAP),
        jsonObject.getInt(COIN_RANK),
        jsonObject.getLong(COIN_VOLUME),
        jsonObject.getDouble(COIN_HIGH),
        jsonObject.getDouble(COIN_LOW),
        jsonObject.getDouble(COIN_MAX_HIGH),
        jsonObject.getDouble(COIN_MAX_LOW),
        jsonObject.optInt(COIN_TOTAL),
        jsonObject.optInt(COIN_MAX),
        jsonObject.optInt(COIN_CURRENT)
    )
    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(COIN_KEY_ID)),
        cursor.getString(cursor.getColumnIndex(COIN_KEY_NAME)),
        DEFAULT_STRING,
        cursor.getString(cursor.getColumnIndex(COIN_KEY_IMAGE)),
       DEFAULT_DOUBLE,
        DEFAULT_DOUBLE,
        DEFAULT_LONG,
        DEFAULT_INT,
        DEFAULT_LONG,
        DEFAULT_DOUBLE,
        DEFAULT_DOUBLE,
        DEFAULT_DOUBLE,
        DEFAULT_DOUBLE,
        DEFAULT_INT,
        DEFAULT_INT,
        DEFAULT_INT
    )

    fun getContentValue() = ContentValues().apply {
        put(COIN_KEY_ID, id)
        put(COIN_KEY_NAME, name)
        put(COIN_KEY_IMAGE, image)
    }

    companion object {
        const val COIN_TABLE_NAME = "coin"
        const val COIN_KEY_ID = "id"
        const val COIN_KEY_NAME = "name"
        const val COIN_KEY_IMAGE = "image"
    }
}
