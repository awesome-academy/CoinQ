package com.sunasterisk.coinqapp.data.source.remote.api

data class RequestCoins(
    val vsCurrency: String,
    val order: String,
    val perPage: Int,
    val page: Int,
    val sparkline: Boolean,
    val changeDay: String
)

data class RequestCoinDetail(
    val id: String,
    val localization: Boolean,
    val ticker: Boolean,
    val marketData: Boolean,
    val community: Boolean,
    val dev: Boolean
)
