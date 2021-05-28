package com.sunasterisk.coinqapp.data.model

import android.os.Parcelable
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_HIGH
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_ID
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_IMAGE
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_LOW
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_MAX_HIGH
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_NAME
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_PRICE
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_RANK
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_SYMBOL
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_VOLUME
import com.sunasterisk.coinqapp.utils.CoinConstant.DAY_CHANGE
import com.sunasterisk.coinqapp.utils.CoinConstant.MARKET_CAP
import com.sunasterisk.coinqapp.utils.CoinConstant.SPARKLINE_PRICE
import com.sunasterisk.coinqapp.utils.CoinConstant.SPARKLINE_WEEK
import com.sunasterisk.coinqapp.utils.parseToArray
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
    val volume: Int,
    val high: Double,
    val low: Double,
    val maxHigh: Double,
    val spark_line: List<Double>,
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
        jsonObject.getInt(COIN_VOLUME),
        jsonObject.getDouble(COIN_HIGH),
        jsonObject.getDouble(COIN_LOW),
        jsonObject.getDouble(COIN_MAX_HIGH),
        jsonObject.getJSONObject(SPARKLINE_WEEK).getJSONArray(SPARKLINE_PRICE).parseToArray()
    )
}
