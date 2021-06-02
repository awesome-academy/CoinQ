package com.sunasterisk.coinqapp.data.model

import com.sunasterisk.coinqapp.utils.CoinDetailConstant.BLOCK_CHAIN_SITE
import com.sunasterisk.coinqapp.utils.CoinDetailConstant.DESCRIPTION
import com.sunasterisk.coinqapp.utils.CoinDetailConstant.ENGLISH
import com.sunasterisk.coinqapp.utils.CoinDetailConstant.FORUM_URL
import com.sunasterisk.coinqapp.utils.CoinDetailConstant.HOME_PAGE
import com.sunasterisk.coinqapp.utils.CoinDetailConstant.LINKS
import org.json.JSONObject

data class CoinDetail(
    val homePage: String,
    val blockChainSite: String,
    val forumSite: String,
    val description: String
){
    constructor(jsonObject: JSONObject): this(
        jsonObject.getJSONObject(LINKS).getJSONArray(HOME_PAGE).getString(0),
        jsonObject.getJSONObject(LINKS).getJSONArray(BLOCK_CHAIN_SITE).getString(0),
        jsonObject.getJSONObject(LINKS).getJSONArray(FORUM_URL).getString(0),
        jsonObject.getJSONObject(DESCRIPTION).getString(ENGLISH)
    )
}
