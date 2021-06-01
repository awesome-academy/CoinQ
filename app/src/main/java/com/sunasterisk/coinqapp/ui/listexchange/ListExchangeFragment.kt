package com.sunasterisk.coinqapp.ui.listexchange

import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.repository.ExchangeRepository
import com.sunasterisk.coinqapp.data.source.remote.ExchangeRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentListExchangeBinding
import com.sunasterisk.coinqapp.ui.exchangedetail.ExchangeDetailFragment
import com.sunasterisk.coinqapp.ui.listexchange.adapter.ListExchangeAdapter
import com.sunasterisk.coinqapp.utils.addFragment
import com.sunasterisk.coinqapp.utils.showMessage

class ListExchangeFragment : BaseFragment<FragmentListExchangeBinding>(),
    ListExchangeContract.View {

    private var presenter: ListExchangePresenter? = null
    private val adapter = ListExchangeAdapter(::onItemClick)

    override val binding by lazy { FragmentListExchangeBinding.inflate(layoutInflater) }

    override fun initViews() {
        binding.recyclerExchange.adapter = adapter
        presenter = ListExchangePresenter(
            ExchangeRepository.getInstance(ExchangeRemoteDataSource.getInstance()),
            this
        )
        presenter?.getListExchange()
    }

    override fun initListeners() {
    }

    override fun showListExchange(exchanges: List<Exchange>) {
        adapter.updateData(exchanges.toMutableList())
    }

    override fun showError(error: Exception?) {
        context?.showMessage(error.toString())
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
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
