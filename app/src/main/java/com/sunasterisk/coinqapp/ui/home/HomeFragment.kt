package com.sunasterisk.coinqapp.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.base.BaseViewPagerAdapter
import com.sunasterisk.coinqapp.databinding.FragmentHomeBinding
import com.sunasterisk.coinqapp.ui.listcoin.ListCoinFragment
import com.sunasterisk.coinqapp.ui.listexchange.ListExchangeFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val titles by lazy {
        listOf(
            getString(R.string.menu_coin),
            getString(R.string.menu_exchange)
        )
    }
    private val fragments = listOf<Fragment>(ListCoinFragment(), ListExchangeFragment())

    override val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun initViews() {
        val adapter = BaseViewPagerAdapter(
            fragments,
            titles,
            childFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        with(binding) {
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(binding.viewPager)
        }
    }

    override fun initListeners() {
    }
}
