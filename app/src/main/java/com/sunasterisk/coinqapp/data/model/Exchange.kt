package com.sunasterisk.coinqapp.data.model

import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_ID
import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_IMAGE
import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_NAME
import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_TRUST
import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_VOLUME
import org.json.JSONObject

data class Exchange(
    val id: String,
    val name: String,
    val image: String,
    val volume: Long,
    val trustScore: Int
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(EXCHANGE_ID),
        jsonObject.getString(EXCHANGE_NAME),
        jsonObject.getString(EXCHANGE_IMAGE),
        jsonObject.getLong(EXCHANGE_VOLUME),
        jsonObject.getInt(EXCHANGE_TRUST)
    )
}
