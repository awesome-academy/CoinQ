package com.sunasterisk.coinqapp.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_ID
import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_IMAGE
import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_NAME
import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_TRUST
import com.sunasterisk.coinqapp.utils.ExchangeConstant.EXCHANGE_VOLUME
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Exchange(
    val id: String,
    val name: String,
    val image: String,
    val volume: Long,
    val trustScore: Int
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(EXCHANGE_ID),
        jsonObject.getString(EXCHANGE_NAME),
        jsonObject.getString(EXCHANGE_IMAGE),
        jsonObject.getLong(EXCHANGE_VOLUME),
        jsonObject.optInt(EXCHANGE_TRUST)
    )

    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(KEY_ID)),
        cursor.getString(cursor.getColumnIndex(NAME)),
        cursor.getString(cursor.getColumnIndex(IMAGE)),
        cursor.getLong(cursor.getColumnIndex(VOLUMES)),
        cursor.getInt(cursor.getColumnIndex(TRUST_SCORE))
    )

    fun getContentValue() = ContentValues().apply {
        put(KEY_ID, id)
        put(NAME, name)
        put(IMAGE, image)
        put(VOLUMES, volume)
        put(TRUST_SCORE, trustScore)
    }

    companion object {
        const val EXCHANGE_TABLE_NAME = "meal"
        const val KEY_ID = "id"
        const val NAME = "name"
        const val IMAGE = "image"
        const val VOLUMES = "volume"
        const val TRUST_SCORE = "trustScore"
    }
}
