package com.sunasterisk.coinqapp.data.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import com.sunasterisk.coinqapp.data.model.Coin.Companion.COIN_KEY_ID
import com.sunasterisk.coinqapp.data.model.Coin.Companion.COIN_KEY_IMAGE
import com.sunasterisk.coinqapp.data.model.Coin.Companion.COIN_KEY_NAME
import com.sunasterisk.coinqapp.data.model.Coin.Companion.COIN_TABLE_NAME
import com.sunasterisk.coinqapp.data.model.Exchange.Companion.EXCHANGE_TABLE_NAME
import com.sunasterisk.coinqapp.data.model.Exchange.Companion.IMAGE
import com.sunasterisk.coinqapp.data.model.Exchange.Companion.KEY_ID
import com.sunasterisk.coinqapp.data.model.Exchange.Companion.NAME
import com.sunasterisk.coinqapp.data.model.Exchange.Companion.TRUST_SCORE
import com.sunasterisk.coinqapp.data.model.Exchange.Companion.VOLUMES

class AppDataBase private constructor(
    context: Context?,
    dbName: String,
    version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.apply {
            execSQL(CREATE_COIN_TABLE)
            execSQL(CREATE_EXCHANGE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.apply {
            execSQL(DROP_COIN_TABLE)
            execSQL(DROP_EXCHANGE_TABLE)
        }
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "coinQ.db"
        private const val DB_VERSION = 1

        private const val CREATE_COIN_TABLE =
            "CREATE TABLE $COIN_TABLE_NAME (" +
                    "$_ID INTEGER PRIMARY KEY, " +
                    "$COIN_KEY_ID TEXT, " +
                    "$COIN_KEY_NAME TEXT, " +
                    "$COIN_KEY_IMAGE TEXT)"

        private val DROP_COIN_TABLE =
            String.format("DROP TABLE IF EXISTS %s", COIN_TABLE_NAME)

        private const val CREATE_EXCHANGE_TABLE =
            "CREATE TABLE $EXCHANGE_TABLE_NAME (" +
                    "$_ID INTEGER PRIMARY KEY, " +
                    "$KEY_ID TEXT, " +
                    "$NAME TEXT, " +
                    "$IMAGE TEXT, " +
                    "$VOLUMES INTEGER, " +
                    "$TRUST_SCORE INTEGER)"

        private val DROP_EXCHANGE_TABLE =
            String.format("DROP TABLE IF EXISTS %s", EXCHANGE_TABLE_NAME)

        private val lock = Any()
        private var instance: AppDataBase? = null

        fun getInstance(context: Context?) =
            instance ?: synchronized(lock) {
                instance ?: AppDataBase(context, DB_NAME, DB_VERSION).also { instance = it }
            }
    }
}
