package com.sunasterisk.coinqapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.view.ViewGroup
import android.widget.Toast
import com.sunasterisk.coinqapp.R

fun Context.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.checkInternet() : Boolean{
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    if (networkInfo == null || !networkInfo.isConnected || !networkInfo.isAvailable) {
        val dialog = Dialog(this)
        with(dialog) {
            setContentView(R.layout.alert_dialog)
            setCanceledOnTouchOutside(true)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
        }
        return false
    }
    return true
}
