package com.sunasterisk.coinqapp.data.model

import org.json.JSONArray

data class CoinEntry(
    val open: Float,
    val high: Float,
    val low: Float,
    val close: Float
) {
    constructor(jsonArray: JSONArray) : this(
        jsonArray.getDouble(1).toFloat(),
        jsonArray.getDouble(2).toFloat(),
        jsonArray.getDouble(3).toFloat(),
        jsonArray.getDouble(4).toFloat()
    )
}
