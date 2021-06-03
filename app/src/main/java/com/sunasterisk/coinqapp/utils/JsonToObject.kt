package com.sunasterisk.coinqapp.utils

import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.data.model.*
import org.json.JSONArray
import org.json.JSONException

inline fun <reified T> JSONArray.parseJsonToObject() = this.run {
    List(length()) { index ->
        when (T::class) {
            Coin::class -> Coin(getJSONObject(index)) as T
            CoinDetail::class -> CoinDetail(getJSONObject(index)) as T
            CoinEntry::class -> CoinEntry(getJSONArray(index)) as T
            Exchange::class -> Exchange(getJSONObject(index)) as T
            ExchangeDetail::class -> ExchangeDetail(getJSONObject(index)) as T
            ExchangeEntry::class -> ExchangeEntry(getJSONArray(index)) as T
            else -> throw JSONException(getString(R.string.error_parse_json))
        }
    }
}
