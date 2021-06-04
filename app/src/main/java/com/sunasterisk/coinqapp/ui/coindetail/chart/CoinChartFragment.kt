package com.sunasterisk.coinqapp.ui.coindetail.chart

import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.core.os.bundleOf
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.model.CoinEntry
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.source.local.CoinLocalDataSource
import com.sunasterisk.coinqapp.data.source.local.dao.CoinDaoImpl
import com.sunasterisk.coinqapp.data.source.local.db.AppDataBase
import com.sunasterisk.coinqapp.data.source.remote.CoinRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentCoinChartBinding
import com.sunasterisk.coinqapp.utils.CustomProgressBar
import com.sunasterisk.coinqapp.utils.Pattern.PATTERN_TEXT_MARKET
import com.sunasterisk.coinqapp.utils.Pattern.PATTERN_TEXT_PRICE
import com.sunasterisk.coinqapp.utils.Pattern.PATTERN_TEXT_SUPPLY
import com.sunasterisk.coinqapp.utils.Pattern.PATTERN_TEX_DAY
import com.sunasterisk.coinqapp.utils.Pattern.formatToPattern
import com.sunasterisk.coinqapp.utils.showMessage

class CoinChartFragment : BaseFragment<FragmentCoinChartBinding>(), CoinChartContract.View {

    private var presenter: CoinChartContract.Presenter? = null
    private var coin: Coin? = null
    private val loadingProgressBar = CustomProgressBar()
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
                    in 3..8 -> texts[i].text =
                        textValues[i]?.let { formatToPattern(PATTERN_TEXT_PRICE, it) }
                    in 9..10 -> texts[i].text =
                        textValues[i]?.let { formatToPattern(PATTERN_TEXT_MARKET, it) }
                    else -> texts[i].text =
                        textValues[i]?.let { formatToPattern(PATTERN_TEXT_SUPPLY, it) }
                }
            }
        }
        presenter =
            CoinChartPresenter(
                CoinRepository.getInstance(
                    CoinRemoteDataSource.getInstance(),
                    CoinLocalDataSource.getInstance(
                        CoinDaoImpl.getInstance(
                            AppDataBase.getInstance(context)
                        )
                    )
                ), this
            )
        coin?.let { presenter?.getCoinChart(it.id) }
    }

    override fun initListeners() {
    }

    override fun showCoinChart(coinEntries: List<CoinEntry>) {
        val candleEntries = coinEntries.mapIndexed { index, entry ->
            CandleEntry(
                index.toFloat(),
                entry.high,
                entry.low,
                entry.open,
                entry.close
            )
        }
        val candleDataSet = CandleDataSet(candleEntries, coin?.name)
        with(candleDataSet) {
            shadowColor = Color.BLACK
            shadowWidth = SHADOW_WIDTH
            decreasingColor = resources.getColor(R.color.color_pomegranate)
            decreasingPaintStyle = Paint.Style.FILL
            increasingColor = resources.getColor(R.color.green_bot_nav)
            setDrawValues(false)
            increasingPaintStyle = Paint.Style.FILL
            neutralColor = Color.BLUE
        }
        val candleData = CandleData(candleDataSet)
        with(binding) {
            coinChart.apply {
                axisLeft.isEnabled = false
                xAxis.setDrawLabels(false)
                description.isEnabled = false
                legend.isEnabled = false
                data = candleData
                invalidate()
            }
        }
    }

    override fun showError(error: Exception?) {
        context?.showMessage(getString(R.string.error_get_data))
    }

    override fun showLoading() {
        context?.let {
            loadingProgressBar.showProgressBar(it, getString(R.string.msg_please_wait))
        }
    }

    override fun hideLoading() {
        Handler(Looper.myLooper()!!).postDelayed({
            loadingProgressBar.dialog.dismiss()
        }, CustomProgressBar.DIALOG_MIN_TIME)
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
        private const val SHADOW_WIDTH = 0.7f

        fun newInstance(coin: Coin) = CoinChartFragment().apply {
            arguments = bundleOf(BUNDLE_COIN to coin)
        }
    }
}
