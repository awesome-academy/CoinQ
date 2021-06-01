package com.sunasterisk.coinqapp.ui.coindetail

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.base.BaseViewPagerAdapter
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.databinding.FragmentCoinDetailBinding
import com.sunasterisk.coinqapp.ui.coindetail.chart.CoinChartFragment
import com.sunasterisk.coinqapp.ui.coindetail.info.CoinInfoFragment
import com.sunasterisk.coinqapp.ui.home.HomeFragment
import com.sunasterisk.coinqapp.utils.backPress
import com.sunasterisk.coinqapp.utils.loadImage

class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>() {

    private var coin: Coin? = null
    private var fragments: List<Fragment>? = null
    private var titles: List<String>? = null

    override val binding by lazy { FragmentCoinDetailBinding.inflate(layoutInflater) }

    override fun initViews() {
        showToolBarTitle()
        initTabLayout()
    }

    override fun initListeners() {
        with(binding) {
            buttonBack.setOnClickListener {
                backPress(parentFragmentManager, HomeFragment())
            }
        }
    }

    private fun showToolBarTitle() {
        coin = arguments?.getParcelable(BUNDLE_COIN)
        with(binding) {
            coin?.apply {
                imageCoin.loadImage(image)
                textCoinName.text = name
                textCoinSymbol.text = symbol
            }
        }
    }

    private fun initTabLayout() {
        coin?.let {
            fragments = listOf<Fragment>(
                CoinChartFragment.newInstance(it),
                CoinInfoFragment.newInstance(it.id)
            )
            titles = listOf(getString(R.string.menu_tab_price), getString(R.string.menu_tab_info))
        }
        val adapter = fragments?.let {
            titles?.let { titles ->
                BaseViewPagerAdapter(
                    it,
                    titles,
                    childFragmentManager
                )
            }
        }
        with(binding) {
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    companion object {
        private const val BUNDLE_COIN = "BUNDLE_COIN"

        fun newInstance(coin: Coin) = CoinDetailFragment().apply {
            arguments = bundleOf(BUNDLE_COIN to coin)
        }
    }
}
