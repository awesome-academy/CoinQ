package com.sunasterisk.coinqapp.ui.exchangedetail

import android.graphics.Color
import android.os.Handler
import androidx.core.os.bundleOf
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.model.ExchangeDetail
import com.sunasterisk.coinqapp.data.model.ExchangeEntry
import com.sunasterisk.coinqapp.data.repository.ExchangeRepository
import com.sunasterisk.coinqapp.data.source.remote.ExchangeRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentExchangeDetailBinding
import com.sunasterisk.coinqapp.ui.home.HomeFragment
import com.sunasterisk.coinqapp.utils.CustomProgressBar
import com.sunasterisk.coinqapp.utils.backPress
import com.sunasterisk.coinqapp.utils.loadImage
import com.sunasterisk.coinqapp.utils.showMessage

class ExchangeDetailFragment : BaseFragment<FragmentExchangeDetailBinding>(),
    ExchangeDetailContract.View {

    private var exchange: Exchange? = null
    private var presenter: ExchangeDetailContract.Presenter? = null
    private val loadingProgressBar by lazy { CustomProgressBar() }

    override val binding by lazy { FragmentExchangeDetailBinding.inflate(layoutInflater) }

    override fun initViews() {
        showToolBarTitle()
        presenter =
            ExchangeDetailPresenter(
                this,
                ExchangeRepository.getInstance(ExchangeRemoteDataSource.getInstance())
            )
        exchange?.let {
            presenter?.getExchangeDetail(it.id)
            presenter?.getExchangeChart(it.id, DAYS)
        }
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

    override fun showExchangeDetail(exchangeDetail: ExchangeDetail) {
        with(binding) {
            exchangeDetail.apply {
                textYearNumber.text = yearEstablished.toString()
                textHomePageURL.text = homePage
                textFacebookURL.text = facebook
                textRedisURL.text = redis
            }
        }
    }

    override fun showExchangeChart(exchangeEntries: List<ExchangeEntry>) {
        val entries = exchangeEntries.mapIndexed { index, exchangeEntry ->
            Entry(
                index.toFloat(),
                exchangeEntry.volume
            )
        }
        val lineDataSet = LineDataSet(entries, null)
        lineDataSet.apply {
            setDrawIcons(false)
            setDrawCircleHole(false)
            setDrawCircles(false)
            axisDependency = YAxis.AxisDependency.LEFT
            color = resources.getColor(R.color.green_bot_nav)
            lineWidth = LINE_WIDTH
        }
        val lineData = LineData(lineDataSet)
        binding.lineChart.apply {
            xAxis.setDrawLabels(false)
            setBackgroundColor(Color.WHITE)
            setTouchEnabled(true)
            setScaleEnabled(true)
            axisLeft.isEnabled = false
            description.isEnabled = false
            data = lineData
        }
    }

    override fun showError(error: Exception?) {
        context?.showMessage(error?.message.toString())
    }

    override fun showLoading() {
        context?.let {
            loadingProgressBar.showProgressBar(it, getString(R.string.msg_please_wait))
        }
    }

    override fun hideLoading() {
        Handler().postDelayed({
            loadingProgressBar.dialog.dismiss()
        }, CustomProgressBar.DIALOG_MIN_TIME)
    }

    companion object {
        private const val BUNDLE_EXCHANGE = "BUNDLE_EXCHANGE"
        private const val DAYS = 30
        private const val LINE_WIDTH = 1f

        fun newInstance(exchange: Exchange) = ExchangeDetailFragment().apply {
            arguments = bundleOf(BUNDLE_EXCHANGE to exchange)
        }
    }
}
