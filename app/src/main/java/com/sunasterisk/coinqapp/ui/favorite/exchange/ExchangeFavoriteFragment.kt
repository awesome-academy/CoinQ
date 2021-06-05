package com.sunasterisk.coinqapp.ui.favorite.exchange

import android.os.Handler
import android.os.Looper
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.repository.ExchangeRepository
import com.sunasterisk.coinqapp.data.source.local.ExchangeLocalDataSource
import com.sunasterisk.coinqapp.data.source.local.dao.ExchangeDaoImpl
import com.sunasterisk.coinqapp.data.source.local.db.AppDataBase
import com.sunasterisk.coinqapp.data.source.remote.ExchangeRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentFavoriteExchangeBinding
import com.sunasterisk.coinqapp.ui.exchangedetail.ExchangeDetailFragment
import com.sunasterisk.coinqapp.ui.favorite.exchange.adapter.ExchangeFavoriteAdapter
import com.sunasterisk.coinqapp.utils.CustomProgressBar
import com.sunasterisk.coinqapp.utils.replaceFragment
import com.sunasterisk.coinqapp.utils.showMessage

class ExchangeFavoriteFragment : BaseFragment<FragmentFavoriteExchangeBinding>(),
    ExchangeFavoriteContract.View {

    private var presenter: ExchangeFavoriteContract.Presenter? = null
    private val adapter by lazy { ExchangeFavoriteAdapter(::onItemClick) }
    private val loadingProgressBar by lazy { CustomProgressBar() }

    override val binding by lazy {
        FragmentFavoriteExchangeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.recyclerExchanges.adapter = adapter
        presenter = ExchangeFavoritePresenter(
            this,
            ExchangeRepository.getInstance(
                ExchangeRemoteDataSource.getInstance(),
                ExchangeLocalDataSource.getInstance(
                    ExchangeDaoImpl.getInstance(
                        AppDataBase.getInstance(context)
                    )
                )
            )
        )
        presenter?.getExchangesFavorite()
    }

    override fun initListeners() {
    }

    override fun showExchangesFavorite(exchanges: List<Exchange>) {
        adapter.updateData(exchanges.toMutableList())
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

    private fun onItemClick(exchange: Exchange) {
        parentFragment?.apply {
            replaceFragment(
                parentFragmentManager,
                R.id.frameContainer,
                ExchangeDetailFragment.newInstance(exchange)
            )
        }
    }
}
