package com.sunasterisk.coinqapp.ui.calculator

import android.os.Handler
import android.widget.EditText
import android.widget.TextView
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.source.local.CoinLocalDataSource
import com.sunasterisk.coinqapp.data.source.local.dao.CoinDaoImpl
import com.sunasterisk.coinqapp.data.source.local.db.AppDataBase
import com.sunasterisk.coinqapp.data.source.remote.CoinRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentCalculatorBinding
import com.sunasterisk.coinqapp.ui.calculator.adapter.CalculatorAdapter
import com.sunasterisk.coinqapp.ui.setting.SettingFragment
import com.sunasterisk.coinqapp.utils.*
import com.sunasterisk.coinqapp.utils.Default.DEFAULT_DOUBLE_ONE

class CalculatorFragment : BaseFragment<FragmentCalculatorBinding>(), CalculatorContract.View {

    private var adapter = CalculatorAdapter(::onItemClick)
    private var coinsData = mutableListOf<Coin>()
    private var presenter: CalculatorContract.Presenter? = null
    private val loadingProgressBar by lazy { CustomProgressBar() }

    override val binding by lazy {
        FragmentCalculatorBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.recyclerCoins.adapter = adapter
        presenter = CalculatorPresenter(
            this,
            CoinRepository.getInstance(
                CoinRemoteDataSource.getInstance(),
                CoinLocalDataSource.getInstance(
                    CoinDaoImpl.getInstance(
                        AppDataBase.getInstance(context)
                    )
                )
            )
        )
        if (requireContext().checkInternet()) {
            presenter?.getCoins()
        }
    }

    override fun initListeners() {
        with(binding) {
            buttonBack.setOnClickListener {
                backPress(parentFragmentManager, SettingFragment())
            }
            editTextSearch.setOnEditorActionListener(onEditTextListener())
        }
    }

    override fun showCoins(coins: List<Coin>) {
        coinsData = coins.toMutableList()
        adapter.updateNewData(coinsData, DEFAULT_DOUBLE_ONE)
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

    private fun onEditTextListener() = TextView.OnEditorActionListener { _, _, _ ->
        searchListener(binding.editTextSearch)
        true
    }

    private fun searchListener(editText: EditText) {
        val key = editText.text.toString()
        if (key.isEmpty()) {
            context?.showMessage(getString(R.string.msg_fill_search))
        } else {
            adapter.updateNewData(coinsData, key.toDouble())
            context?.let { editText.closeKeyboard(it) }
        }
    }

    private fun onItemClick(coin: Coin) {
    }
}
