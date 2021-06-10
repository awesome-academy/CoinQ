package com.sunasterisk.coinqapp.ui.listexchange

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
import com.sunasterisk.coinqapp.databinding.FragmentListExchangeBinding
import com.sunasterisk.coinqapp.ui.exchangedetail.ExchangeDetailFragment
import com.sunasterisk.coinqapp.ui.listexchange.adapter.ListExchangeAdapter
import com.sunasterisk.coinqapp.utils.CustomProgressBar
import com.sunasterisk.coinqapp.utils.addFragment
import com.sunasterisk.coinqapp.utils.checkInternet
import com.sunasterisk.coinqapp.utils.showMessage

class ListExchangeFragment : BaseFragment<FragmentListExchangeBinding>(),
    ListExchangeContract.View {

    private var presenter: ListExchangePresenter? = null
    private val adapter = ListExchangeAdapter(::onItemClick)
    private val loadingProgressBar = CustomProgressBar()

    override val binding by lazy { FragmentListExchangeBinding.inflate(layoutInflater) }

    override fun initViews() {
        binding.recyclerExchange.adapter = adapter
        presenter = ListExchangePresenter(
            ExchangeRepository.getInstance(
                ExchangeRemoteDataSource.getInstance(),
                ExchangeLocalDataSource.getInstance(
                    ExchangeDaoImpl.getInstance(
                        AppDataBase.getInstance(context)
                    )
                )
            ),
            this
        )
        if (requireContext().checkInternet()) {
            presenter?.getListExchange()
        }
    }

    override fun initListeners() {
    }

    override fun showListExchange(exchanges: List<Exchange>) {
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
            addFragment(
                parentFragmentManager,
                R.id.frameContainer,
                ExchangeDetailFragment.newInstance(exchange)
            )
        }
    }
}
