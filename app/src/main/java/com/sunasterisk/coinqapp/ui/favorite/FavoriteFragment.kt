package com.sunasterisk.coinqapp.ui.favorite

import androidx.fragment.app.Fragment
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.base.BaseViewPagerAdapter
import com.sunasterisk.coinqapp.databinding.FragmentFavoriteBinding
import com.sunasterisk.coinqapp.ui.favorite.coin.CoinFavoriteFragment
import com.sunasterisk.coinqapp.ui.favorite.exchange.ExchangeFavoriteFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val fragments by lazy {
        listOf<Fragment>(
            CoinFavoriteFragment(),
            ExchangeFavoriteFragment()
        )
    }
    private val titles by lazy {
        listOf(
            getString(R.string.menu_coin),
            getString(R.string.menu_exchange)
        )
    }

    override val binding by lazy { FragmentFavoriteBinding.inflate(layoutInflater) }

    override fun initViews() {
        val adapter = BaseViewPagerAdapter(
            fragments,
            titles,
            childFragmentManager
        )
        with(binding)
        {
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    override fun initListeners() {
    }
}
