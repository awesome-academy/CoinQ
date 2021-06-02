package com.sunasterisk.coinqapp.utils

import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.model.CoinEntry
import com.sunasterisk.coinqapp.data.model.Exchange
import org.json.JSONArray
import org.json.JSONException

inline fun <reified T> JSONArray.parseJsonToObject() = this.run {
    List(length()) { index ->
        when (T::class) {
            Coin::class -> Coin(getJSONObject(index)) as T
            CoinEntry::class -> CoinEntry(getJSONArray(index)) as T
            Exchange::class -> Exchange(getJSONObject(index)) as T
            else -> throw JSONException(getString(R.string.error_parse_json))
        }
    }
}

fun JSONArray.parseToArray(): List<Double> {
    val array = mutableListOf<Double>()
    for (i in 0 until length()) {
        array.add(getDouble(i))
    }
    return array
}
