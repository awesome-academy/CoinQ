package com.sunasterisk.coinqapp.data.source.remote.api

import android.net.Uri
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.API_CONTENT
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.API_VER
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.BASE_URL
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.COIN
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.EXCHANGE
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_COMMUNITY
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_DAY
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_DAYS
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_DEV_DATA
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_ID
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_LOCALIZATION
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_MARKET_DATA
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_ORDER
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_PAGE
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_PER_PAGE
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_SPARK_LINE
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_TICKER
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.FILTER_USD
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.MARKET
import com.sunasterisk.coinqapp.data.source.remote.api.APIConstant.SCHEME

object APIQuery {

    fun queryCoins(requestCoins: RequestCoins) = Uri.Builder().scheme(SCHEME)
        .authority(BASE_URL)
        .appendPath(API_CONTENT)
        .appendPath(API_VER)
        .appendPath(COIN)
        .appendPath(MARKET)
        .appendQueryParameter(FILTER_USD, requestCoins.vsCurrency)
        .appendQueryParameter(FILTER_ORDER, requestCoins.order)
        .appendQueryParameter(FILTER_PER_PAGE, requestCoins.perPage.toString())
        .appendQueryParameter(FILTER_PAGE, requestCoins.page.toString())
        .appendQueryParameter(FILTER_SPARK_LINE, requestCoins.sparkline.toString())
        .appendQueryParameter(FILTER_DAY, requestCoins.changeDay)
        .toString()

    fun queryCoinDetail(requestCoinDetail: RequestCoinDetail) = Uri.Builder().scheme(SCHEME)
        .authority(BASE_URL)
        .appendPath(API_CONTENT)
        .appendPath(API_VER)
        .appendQueryParameter(FILTER_ID, requestCoinDetail.id)
        .appendQueryParameter(FILTER_LOCALIZATION, requestCoinDetail.localization.toString())
        .appendQueryParameter(FILTER_TICKER, requestCoinDetail.ticker.toString())
        .appendQueryParameter(FILTER_MARKET_DATA, requestCoinDetail.marketData.toString())
        .appendQueryParameter(FILTER_COMMUNITY, requestCoinDetail.community.toString())
        .appendQueryParameter(FILTER_DEV_DATA, requestCoinDetail.dev.toString())
        .toString()

    fun queryExchanges(perPage: Int, page: Int) = Uri.Builder().scheme(SCHEME)
        .authority(BASE_URL)
        .appendPath(API_CONTENT)
        .appendPath(API_VER)
        .appendPath(EXCHANGE)
        .appendQueryParameter(FILTER_PER_PAGE, perPage.toString())
        .appendQueryParameter(FILTER_PAGE, page.toString())
        .toString()

    fun queryExchangeDetail(id: String) = Uri.Builder().scheme(SCHEME)
        .authority(BASE_URL)
        .appendPath(API_CONTENT)
        .appendPath(API_VER)
        .appendPath(EXCHANGE)
        .appendQueryParameter(FILTER_ID, id)
        .toString()

    fun queryExchangeChart(id: String, days: Int) = Uri.Builder().scheme(SCHEME)
        .authority(BASE_URL)
        .appendPath(API_CONTENT)
        .appendPath(API_VER)
        .appendPath(EXCHANGE)
        .appendQueryParameter(FILTER_ID, id)
        .appendQueryParameter(FILTER_DAYS, days.toString())
        .toString()
}
