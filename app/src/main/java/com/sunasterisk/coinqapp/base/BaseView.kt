package com.sunasterisk.coinqapp.base

interface BaseView {
    fun showError(error : Exception?)
    fun showLoading()
    fun hideLoading()
}
