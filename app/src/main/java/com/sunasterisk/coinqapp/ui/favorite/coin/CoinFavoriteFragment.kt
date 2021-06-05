package com.sunasterisk.coinqapp.ui.favorite.coin

import android.os.Handler
import android.os.Looper
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.source.local.CoinLocalDataSource
import com.sunasterisk.coinqapp.data.source.local.dao.CoinDaoImpl
import com.sunasterisk.coinqapp.data.source.local.db.AppDataBase
import com.sunasterisk.coinqapp.data.source.remote.CoinRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentFavoriteCoinBinding
import com.sunasterisk.coinqapp.ui.coindetail.CoinDetailFragment
import com.sunasterisk.coinqapp.ui.favorite.coin.adapter.CoinFavoriteAdapter
import com.sunasterisk.coinqapp.utils.CustomProgressBar
import com.sunasterisk.coinqapp.utils.replaceFragment
import com.sunasterisk.coinqapp.utils.showMessage

class CoinFavoriteFragment : BaseFragment<FragmentFavoriteCoinBinding>(),
    CoinFavoriteContract.View {

    private var presenter: CoinFavoriteContract.Presenter? = null
    private var cryptol: Coin? = null
    private val adapter by lazy { CoinFavoriteAdapter(::onItemClick) }
    private val loadingProgressBar by lazy { CustomProgressBar() }

    override val binding by lazy {
        FragmentFavoriteCoinBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        binding.recyclerCoins.adapter = adapter
        presenter = CoinFavoritePresenter(
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
        presenter?.getCoinsFavorite()
    }

    override fun initListeners() {
    }

    override fun showCoinsFavorite(coins: List<Coin>) {
        adapter.updateData(coins.toMutableList())
    }

    override fun showCoin(coin: Coin) {
        cryptol = coin
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

    private fun onItemClick(coin: Coin) {
        presenter?.getCoin(coin.id)
        cryptol?.let {
            parentFragment?.apply {
                replaceFragment(
                    parentFragmentManager,
                    R.id.frameContainer,
                    CoinDetailFragment.newInstance(it)
                )
            }
        }
    }
}
