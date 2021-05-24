package com.sunasterisk.coinqapp.data.model

import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_HIGH
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_ID
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_IMAGE
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_LOW
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_MAX_HIGH
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_PRICE
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_RANK
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_SYMBOL
import com.sunasterisk.coinqapp.utils.CoinConstant.COIN_VOLUME
import com.sunasterisk.coinqapp.utils.CoinConstant.DAY_CHANGE
import com.sunasterisk.coinqapp.utils.CoinConstant.MARKET_CAP
import org.json.JSONObject

data class Coin(
    val id: String,
    val symbol: String,
    val image : String,
    val price: Int,
    val dayChange: Double,
    val marketCap: Int,
    val rank : Int,
    val volume : Int,
    val high : Int,
    val low : Int,
    val maxHigh : Int,
) {
    constructor(jsonObject: JSONObject) : this (
        jsonObject.getString(COIN_ID),
        jsonObject.getString(COIN_SYMBOL),
        jsonObject.getString(COIN_IMAGE),
        jsonObject.getInt(COIN_PRICE),
        jsonObject.getDouble(DAY_CHANGE),
        jsonObject.getInt(MARKET_CAP),
        jsonObject.getInt(COIN_RANK),
        jsonObject.getInt(COIN_VOLUME),
        jsonObject.getInt(COIN_HIGH),
        jsonObject.getInt(COIN_LOW),
        jsonObject.getInt(COIN_MAX_HIGH)
    )
}
