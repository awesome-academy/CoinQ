package com.sunasterisk.coinqapp.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun getJsonFromUrl(uri: String): String {
    val url = URL(uri)
    val builder = StringBuilder()
    var connection: HttpURLConnection? = null
    var inputStreamReader: InputStreamReader? = null
    var buffer: BufferedReader? = null
    try {
        connection = url.openConnection() as HttpURLConnection
        inputStreamReader = InputStreamReader(connection.inputStream)
        buffer = BufferedReader(inputStreamReader)
        buffer.forEachLine { builder.append(it) }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        connection?.disconnect()
        inputStreamReader?.close()
    }
    return builder.toString()
}
