package com.sunasterisk.coinqapp.utils

import android.os.AsyncTask

class LoadDataAsyncTask<T>(
    private val callback: OnLoadDataCallBack<T>,
    private val handler: () -> T?
) : AsyncTask<Unit, Unit, T?>() {

    private var error: Exception? = null

    override fun doInBackground(vararg params: Unit?): T? {
        return try {
            handler()
        } catch (e: Exception) {
            error = e
            null
        }
    }

    override fun onPostExecute(result: T?) {
        super.onPostExecute(result)
        return result?.let { callback.onSuccess(it) } ?: callback.onFailure(error)
    }
}
