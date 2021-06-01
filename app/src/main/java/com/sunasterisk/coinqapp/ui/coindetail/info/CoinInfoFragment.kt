package com.sunasterisk.coinqapp.ui.coindetail.info

import androidx.core.os.bundleOf
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.databinding.FragmentCoinInfoBinding

class CoinInfoFragment : BaseFragment<FragmentCoinInfoBinding>() {

    override val binding by lazy { FragmentCoinInfoBinding.inflate(layoutInflater) }

    override fun initViews() {
    }

    override fun initListeners() {
    }

    companion object {
        private const val BUNDLE_COIN_ID = "BUNDLE_COIN_ID"

        fun newInstance(coinId: String) = CoinInfoFragment().apply {
            arguments = bundleOf(BUNDLE_COIN_ID to coinId)
        }
    }
}
