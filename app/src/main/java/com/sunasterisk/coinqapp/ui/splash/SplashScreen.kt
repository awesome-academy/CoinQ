package com.sunasterisk.coinqapp.ui.splash

import android.os.Handler
import android.widget.Toast
import com.sunasterisk.coinqapp.base.BaseActivity
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.source.CoinDataSource
import com.sunasterisk.coinqapp.data.source.remote.CoinRemoteDataSource
import com.sunasterisk.coinqapp.data.source.remote.api.RequestCoins
import com.sunasterisk.coinqapp.databinding.SplashActivityBinding
import com.sunasterisk.coinqapp.ui.MainActivity
import com.sunasterisk.coinqapp.utils.OnLoadDataCallBack
import java.lang.Exception

class SplashScreen : BaseActivity<SplashActivityBinding>() {

    override val binding by lazy { SplashActivityBinding.inflate(layoutInflater) }
    private val SPLASH_TIME: Long = 3000

    override fun createView() {
        Handler().postDelayed({
            startActivity(MainActivity.getIntent(this))
            finish()
        }, SPLASH_TIME)
    }
}
