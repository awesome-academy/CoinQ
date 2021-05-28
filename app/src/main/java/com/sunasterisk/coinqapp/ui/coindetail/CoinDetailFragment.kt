package com.sunasterisk.coinqapp.ui.coindetail

import androidx.core.os.bundleOf
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.databinding.FragmentCoinDetailBinding
import com.sunasterisk.coinqapp.ui.home.HomeFragment
import com.sunasterisk.coinqapp.utils.backPress
import com.sunasterisk.coinqapp.utils.loadImage

class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>() {

    private var coin: Coin? = null

    override val binding by lazy { FragmentCoinDetailBinding.inflate(layoutInflater) }

    override fun initViews() {
        showToolBarTitle()
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

    companion object {
        private const val BUNDLE_COIN = "BUNDLE_COIN"
        private const val TAB_PRICE_CHART = "Price Chart"
        private const val TAB_INFO = "Info"

        fun getInstance(coin: Coin) = CoinDetailFragment().apply {
            arguments = bundleOf(BUNDLE_COIN to coin)
        }
    }

}
