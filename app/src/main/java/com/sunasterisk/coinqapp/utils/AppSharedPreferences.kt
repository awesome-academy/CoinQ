package com.sunasterisk.coinqapp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

class AppSharedPreferences private constructor(context: Context) {

    private val preferences = context.getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE)
    private val editor = preferences.edit()

    fun putString(key : String, value: String) {
        editor.apply {
            putString(key, value)
            apply()
        }
    }

    fun getString(key: String, defaultString: String) = preferences.getString(key, defaultString)

    companion object {
        const val PREF_FILE_NAME = "com.sunasterisk.coinqapp.PREF_FILE_NAME"
        private var instance: AppSharedPreferences? = null

        fun getInstance(context: Context) =
            instance ?: AppSharedPreferences(context).also { instance = it }
    }
}
