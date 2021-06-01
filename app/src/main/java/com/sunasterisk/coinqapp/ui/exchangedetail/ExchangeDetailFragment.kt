package com.sunasterisk.coinqapp.ui.exchangedetail

import androidx.core.os.bundleOf
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.databinding.FragmentExchangeDetailBinding
import com.sunasterisk.coinqapp.ui.home.HomeFragment
import com.sunasterisk.coinqapp.utils.backPress
import com.sunasterisk.coinqapp.utils.loadImage

class ExchangeDetailFragment : BaseFragment<FragmentExchangeDetailBinding>() {

    private var exchange: Exchange? = null

    override val binding by lazy { FragmentExchangeDetailBinding.inflate(layoutInflater) }

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
        exchange = arguments?.getParcelable(BUNDLE_EXCHANGE)
        with(binding) {
            exchange?.apply {
                imageExchange.loadImage(image)
                textExchangeName.text = name
            }
        }
    }

    companion object {
        private const val BUNDLE_EXCHANGE = "BUNDLE_EXCHANGE"

        fun newInstance(exchange: Exchange) = ExchangeDetailFragment().apply {
            arguments = bundleOf(BUNDLE_EXCHANGE to exchange)
        }
    }
}
