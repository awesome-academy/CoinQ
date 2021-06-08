package com.sunasterisk.coinqapp.utils

import android.app.Activity
import android.content.res.Configuration
import com.sunasterisk.coinqapp.ui.MainActivity
import java.util.*

fun Activity.setLanguage(language: String) {
    val locale = Locale(language)
    val config = Configuration()
    config.locale = locale
    baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
}

fun Activity.restart() {
    val intent = MainActivity.getIntent(this)
    startActivity(intent)
    finish()
}
