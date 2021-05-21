package com.sunasterisk.coinqapp.utils

import java.lang.Exception

interface OnLoadDataCallBack<T> {
    fun onSuccess(data : T)
    fun onFailure(error : Exception?)
}
