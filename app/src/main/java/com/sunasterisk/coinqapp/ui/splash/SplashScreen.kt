package com.sunasterisk.coinqapp.ui.splash

import android.os.Handler
import com.sunasterisk.coinqapp.base.BaseActivity
import com.sunasterisk.coinqapp.databinding.ActivityMainBinding
import com.sunasterisk.coinqapp.databinding.SplashActivityBinding
import com.sunasterisk.coinqapp.ui.MainActivity

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
