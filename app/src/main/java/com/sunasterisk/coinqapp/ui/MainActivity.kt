package com.sunasterisk.coinqapp.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseActivity
import com.sunasterisk.coinqapp.databinding.ActivityMainBinding
import com.sunasterisk.coinqapp.ui.favorite.FavoriteFragment
import com.sunasterisk.coinqapp.ui.home.HomeFragment
import com.sunasterisk.coinqapp.ui.search.SearchFragment
import com.sunasterisk.coinqapp.ui.setting.SettingFragment
import com.sunasterisk.coinqapp.utils.AppSharedPreferences
import com.sunasterisk.coinqapp.utils.Language.ENGLISH_CODE
import com.sunasterisk.coinqapp.utils.Language.LANGUAGE
import com.sunasterisk.coinqapp.utils.checkInternet
import com.sunasterisk.coinqapp.utils.setLanguage

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val homeFragment = HomeFragment()
    private val favoriteFragment = FavoriteFragment()
    private val searchFragment = SearchFragment()
    private val settingFragment = SettingFragment()
    private var preferences: AppSharedPreferences? = null

    override fun createView() {
        setAppLanguage()
        binding.bottomNavigation.apply {
            setOnNavigationItemSelectedListener(onNavigationSelectedListener)
            selectedItemId = R.id.menu_market
        }
    }

    private val onNavigationSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_market -> addFragment(homeFragment)
                R.id.menu_favorite -> addFragment(favoriteFragment)
                R.id.menu_search -> addFragment(searchFragment)
                R.id.menu_setting -> addFragment(settingFragment)
            }
            true
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.apply {
            popBackStack()
            beginTransaction()
                .replace(R.id.frameContainer, fragment)
                .commit()
        }
    }

    private fun setAppLanguage() {
        preferences = AppSharedPreferences.getInstance(applicationContext)
        val language = preferences?.getString(
            LANGUAGE,
            ENGLISH_CODE
        )
        language?.let { setLanguage(it) }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
