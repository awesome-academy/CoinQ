package com.sunasterisk.coinqapp.data.model

import org.json.JSONArray

data class ExchangeEntry(
    val volume: Float
) {
    constructor(jsonArray: JSONArray) : this(
        jsonArray.getDouble(1).toFloat()
    )
}
