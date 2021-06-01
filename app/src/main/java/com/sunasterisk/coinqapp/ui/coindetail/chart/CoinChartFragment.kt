package com.sunasterisk.coinqapp.ui.coindetail.chart

import android.widget.TextView
import androidx.core.os.bundleOf
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.databinding.FragmentCoinChartBinding
import com.sunasterisk.coinqapp.utils.Pattern.PATTERN_TEXT_MARKET
import com.sunasterisk.coinqapp.utils.Pattern.PATTERN_TEXT_PRICE
import com.sunasterisk.coinqapp.utils.Pattern.PATTERN_TEXT_SUPPLY
import com.sunasterisk.coinqapp.utils.Pattern.PATTERN_TEX_DAY
import com.sunasterisk.coinqapp.utils.Pattern.formatToPattern

class CoinChartFragment : BaseFragment<FragmentCoinChartBinding>(), CoinChartContract.View {

    private var presenter: CoinChartContract.Presenter? = null
    private var coin: Coin? = null
    private val texts by lazy {
        binding.run {
            listOf(
                textRankNumber,
                textCoinSymbol,
                textDayChange,
                textCoinPrice,
                textTotalUSD,
                textDayHighNumber,
                textDayLowNumber,
                textMaxHighNumber,
                textMaxLowNumber,
                textMarketNumber,
                textVolumeNumber,
                textTotalNumber,
                textMaxSupplyNumber,
                textCurrentNumber
            )
        }
    }
    private val textValues by lazy {
        with(coin) {
            listOf(
                this?.rank,
                this?.symbol,
                this?.dayChange,
                this?.price,
                this?.price,
                this?.high,
                this?.low,
                this?.maxHigh,
                this?.maxLow,
                this?.marketCap,
                this?.volume,
                this?.totalSupply,
                this?.maxSupply,
                this?.currentSupply
            )
        }
    }
    override val binding by lazy { FragmentCoinChartBinding.inflate(layoutInflater) }

    override fun initViews() {
        coin = arguments?.getParcelable(BUNDLE_COIN)
        coin?.let {
            for (i in texts.indices) {
                when (i) {
                    0 -> texts[0].text = textValues[0].toString()
                    1 -> texts[1].text = textValues[1].toString()
                    2 -> setColorForText(texts[2], textValues[2] as Double)
                    in 3..8 -> texts[i].text = textValues[i]?.let { formatToPattern(PATTERN_TEXT_PRICE, it) }
                    in 9..10 -> texts[i].text = textValues[i]?.let { formatToPattern(PATTERN_TEXT_MARKET, it) }
                    else -> texts[i].text = textValues[i]?.let { formatToPattern(PATTERN_TEXT_SUPPLY, it) }
                }
            }
        }
    }

    override fun initListeners() {
    }

    override fun showCoinChart() {
    }

    private fun setColorForText(textView: TextView, value: Double) {
        textView.apply {
            text = formatToPattern(PATTERN_TEX_DAY, value / 100)
            if (value >= 0) {
                setTextColor(resources.getColor(R.color.green_bot_nav))
            } else {
                setTextColor(resources.getColor(R.color.color_pomegranate))
            }
        }
    }

    companion object {
        private const val BUNDLE_COIN = "BUNDLE_COIN"

        fun newInstance(coin: Coin) = CoinChartFragment().apply {
            arguments = bundleOf(BUNDLE_COIN to coin)
        }
    }
}
