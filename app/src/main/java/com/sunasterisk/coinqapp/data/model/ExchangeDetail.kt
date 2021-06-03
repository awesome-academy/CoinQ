package com.sunasterisk.coinqapp.data.model

import com.sunasterisk.coinqapp.utils.ExchangeDetailConstant.EXCHANGE_FACEBOOK
import com.sunasterisk.coinqapp.utils.ExchangeDetailConstant.EXCHANGE_REDIS
import com.sunasterisk.coinqapp.utils.ExchangeDetailConstant.EXCHANGE_URL
import com.sunasterisk.coinqapp.utils.ExchangeDetailConstant.YEAR_ESTABLISHED
import org.json.JSONObject

data class ExchangeDetail(
    val yearEstablished: Int,
    val homePage: String,
    val facebook: String,
    val redis: String
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.optInt(YEAR_ESTABLISHED),
        jsonObject.optString(EXCHANGE_URL),
        jsonObject.optString(EXCHANGE_FACEBOOK),
        jsonObject.optString(EXCHANGE_REDIS)
    )
}
