package com.sunasterisk.coinqapp.ui.listcoin

import android.os.Handler
import android.os.Looper
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.source.remote.CoinRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentListCoinBinding
import com.sunasterisk.coinqapp.ui.coindetail.CoinDetailFragment
import com.sunasterisk.coinqapp.ui.listcoin.adapter.ListCoinAdapter
import com.sunasterisk.coinqapp.utils.CustomProgressBar
import com.sunasterisk.coinqapp.utils.addFragment
import com.sunasterisk.coinqapp.utils.showMessage

class ListCoinFragment : BaseFragment<FragmentListCoinBinding>(), ListCoinContract.View {

    private var presenter: ListCoinContract.Presenter? = null
    private val adapter = ListCoinAdapter(::itemCoinClick)
    private val loadingProgressBar = CustomProgressBar()

    override val binding by lazy { FragmentListCoinBinding.inflate(layoutInflater) }

    override fun initViews() {
        binding.apply {
            recyclerCoins.adapter = adapter
        }
        presenter =
            ListCoinPresenter(this, CoinRepository.getInstance(CoinRemoteDataSource.getInstance()))
        presenter?.getListCoin()
    }

    override fun initListeners() {
    }

    override fun showListCoin(coins: List<Coin>) {
        adapter.updateData(coins.toMutableList())
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

    private fun itemCoinClick(coin: Coin) {
        parentFragment?.apply {
            addFragment(
                parentFragmentManager,
                R.id.frameContainer,
                CoinDetailFragment.newInstance(coin)
            )
        }
    }
}

