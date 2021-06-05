package com.sunasterisk.coinqapp.utils

import java.text.DecimalFormat

object CoinConstant {
    const val COIN_ID = "id"
    const val COIN_NAME = "name"
    const val COIN_SYMBOL = "symbol"
    const val COIN_IMAGE = "image"
    const val COIN_PRICE = "current_price"
    const val DAY_CHANGE = "price_change_percentage_24h"
    const val MARKET_CAP = "market_cap"
    const val COIN_RANK = "market_cap_rank"
    const val COIN_VOLUME = "total_volume"
    const val COIN_HIGH = "high_24h"
    const val COIN_LOW = "low_24h"
    const val COIN_MAX_HIGH = "ath"
    const val COIN_MAX_LOW = "atl"
    const val COIN_TOTAL = "total_supply"
    const val COIN_MAX = "max_supply"
    const val COIN_CURRENT = "circulating_supply"
}

object CoinDetailConstant {
    const val LINKS = "links"
    const val HOME_PAGE = "homepage"
    const val BLOCK_CHAIN_SITE = "blockchain_site"
    const val FORUM_URL = "official_forum_url"
    const val DESCRIPTION = "description"
    const val ENGLISH = "en"
}

object ExchangeConstant {
    const val EXCHANGE_ID = "id"
    const val EXCHANGE_NAME = "name"
    const val EXCHANGE_IMAGE = "image"
    const val EXCHANGE_VOLUME = "trade_volume_24h_btc"
    const val EXCHANGE_TRUST = "trust_score"
}

object ExchangeDetailConstant {
    const val YEAR_ESTABLISHED = "year_established"
    const val EXCHANGE_URL = "url"
    const val EXCHANGE_FACEBOOK = "facebook_url"
    const val EXCHANGE_REDIS = "reddit_url"
}

object RequestConstant {
    const val REQUEST_VSCURRENCY = "usd"
    const val REQUEST_ORDER = "market_cap_desc"
    const val REQUEST_SPARKLINE = false
    const val REQUEST_DAYCHANGE = "24h"
    const val REQUEST_PERPAGE = 100
    const val REQUEST_PAGE = 1
}

object Pattern {
    const val PATTERN_TEX_DAY = "#0.00%"
    const val PATTERN_TEXT_PRICE = "$###,###.####"
    const val PATTERN_TEXT_MARKET = "$###,###,##0"
    const val PATTERN_TEXT_NUMBER = "###,###.######"
    const val PATTERN_TEXT_SUPPLY = "###,###,##0"

    fun formatToPattern(pattern: String, value: Any): String = DecimalFormat(pattern).format(value)
}

object Default {
    const val DEFAULT_LONG = 0L
    const val DEFAULT_INT = 0
    const val DEFAULT_DOUBLE = 0.0
    const val DEFAULT_DOUBLE_ONE = 1.0
    const val DEFAULT_STRING = ""
}
