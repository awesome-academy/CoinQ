package com.sunasterisk.coinqapp.ui.search

import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.TextView
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.model.Exchange
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.repository.ExchangeRepository
import com.sunasterisk.coinqapp.data.source.local.CoinLocalDataSource
import com.sunasterisk.coinqapp.data.source.local.ExchangeLocalDataSource
import com.sunasterisk.coinqapp.data.source.local.dao.CoinDaoImpl
import com.sunasterisk.coinqapp.data.source.local.dao.ExchangeDaoImpl
import com.sunasterisk.coinqapp.data.source.local.db.AppDataBase
import com.sunasterisk.coinqapp.data.source.remote.CoinRemoteDataSource
import com.sunasterisk.coinqapp.data.source.remote.ExchangeRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentSearchBinding
import com.sunasterisk.coinqapp.ui.coindetail.CoinDetailFragment
import com.sunasterisk.coinqapp.ui.exchangedetail.ExchangeDetailFragment
import com.sunasterisk.coinqapp.ui.search.adapter.CoinSearchAdapter
import com.sunasterisk.coinqapp.ui.search.adapter.ExchangeSearchAdapter
import com.sunasterisk.coinqapp.utils.*

class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchFragmentContract.View {

    private val coinAdapter by lazy { CoinSearchAdapter(::onItemCoinClick) }
    private val exchangeAdapter by lazy { ExchangeSearchAdapter(::onItemExchangeClick) }
    private var presenter: SearchFragmentContract.Presenter? = null
    private var coinList: List<Coin>? = null
    private var exchangeList: List<Exchange>? = null
    private val loadingProgressBar by lazy { CustomProgressBar() }

    override val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }

    override fun initViews() {
        coinList = listOf()
        exchangeList = listOf()
        with(binding) {
            recyclerCoins.adapter = coinAdapter
            recyclerExchanges.adapter = exchangeAdapter
        }
        presenter =
            SearchFragmentPresenter(
                this, CoinRepository.getInstance(
                    CoinRemoteDataSource.getInstance(),
                    CoinLocalDataSource.getInstance(
                        CoinDaoImpl.getInstance(
                            AppDataBase.getInstance(context)
                        )
                    )
                ),
                ExchangeRepository.getInstance(
                    ExchangeRemoteDataSource.getInstance(),
                    ExchangeLocalDataSource.getInstance(
                        ExchangeDaoImpl.getInstance(
                            AppDataBase.getInstance(context)
                        )
                    )
                )
            )
        presenter?.getCoins()
        presenter?.getExchanges()
    }

    override fun initListeners() {
        with(binding) {
            editTextSearch.setOnEditorActionListener(onEditTextListener())
            buttonClearSearch.setOnClickListener {
                editTextSearch.text = null
                buttonClearSearch.gone()
                buttonSearch.show()
            }
            buttonSearch.setOnClickListener {
                searchListener(editTextSearch)
            }
        }
    }

    override fun updateCoins(coins: List<Coin>) {
        coinList = coins
    }

    override fun showCoinResults(coins: MutableList<Coin>) {
        coinAdapter.updateData(coins)
    }

    override fun updateExchanges(exchanges: List<Exchange>) {
        exchangeList = exchanges
    }

    override fun showExchangeResults(exchanges: MutableList<Exchange>) {
        exchangeAdapter.updateData(exchanges)
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

    private fun onEditTextListener() = TextView.OnEditorActionListener { _, _, _ ->
        searchListener(binding.editTextSearch)
        true
    }

    private fun searchListener(editText: EditText) {
        val key = editText.text.toString()
        if (key.isEmpty()) {
            context?.showMessage(getString(R.string.msg_fill_search))
        } else {
            context?.let { editText.closeKeyboard(it) }
            binding.apply {
                buttonSearch.gone()
                buttonClearSearch.show()
            }
            coinList?.let { presenter?.getCoinsByKey(key, it) }
            exchangeList?.let { presenter?.getExchangesByKey(key, it) }
        }
    }

    private fun onItemCoinClick(coin: Coin) {
        replaceFragment(
            parentFragmentManager,
            R.id.frameContainer,
            CoinDetailFragment.newInstance(coin)
        )
    }

    private fun onItemExchangeClick(exchange: Exchange) {
        replaceFragment(
            parentFragmentManager,
            R.id.frameContainer,
            ExchangeDetailFragment.newInstance(exchange)
        )
    }
}
