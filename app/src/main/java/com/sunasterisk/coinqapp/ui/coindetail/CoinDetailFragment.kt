package com.sunasterisk.coinqapp.ui.coindetail

import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.base.BaseViewPagerAdapter
import com.sunasterisk.coinqapp.data.model.Coin
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.source.local.CoinLocalDataSource
import com.sunasterisk.coinqapp.data.source.local.dao.CoinDaoImpl
import com.sunasterisk.coinqapp.data.source.local.db.AppDataBase
import com.sunasterisk.coinqapp.data.source.remote.CoinRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentCoinDetailBinding
import com.sunasterisk.coinqapp.ui.coindetail.chart.CoinChartFragment
import com.sunasterisk.coinqapp.ui.coindetail.info.CoinInfoFragment
import com.sunasterisk.coinqapp.ui.home.HomeFragment
import com.sunasterisk.coinqapp.utils.*

class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>(), CoinDetailContract.View {

    private var coin: Coin? = null
    private var presenter: CoinDetailPresenter? = null
    private var fragments: List<Fragment>? = null
    private var titles: List<String>? = null

    override val binding by lazy { FragmentCoinDetailBinding.inflate(layoutInflater) }

    override fun initViews() {
        showToolBarTitle()
        initTabLayout()
        presenter = CoinDetailPresenter(
            this, CoinRepository.getInstance(
                CoinRemoteDataSource.getInstance(),
                CoinLocalDataSource.getInstance(
                    CoinDaoImpl.getInstance(
                        AppDataBase.getInstance(context)
                    )
                )
            )
        )
        coin?.let {
            presenter?.getCoinFavorite(it.id)
        }
    }

    override fun initListeners() {
        with(binding) {
            buttonBack.setOnClickListener {
                backPress(parentFragmentManager, HomeFragment())
            }
            buttonFavorite.setOnClickListener {
                it.gone()
                buttonUnFavorite.show()
                coin?.let { coin -> presenter?.deleteCoinFavorite(coin.id) }
            }
            buttonUnFavorite.setOnClickListener {
                it.gone()
                buttonFavorite.show()
                coin?.let { coin -> presenter?.insertCoinFavorite(coin) }
            }
        }
    }

    private fun showToolBarTitle() {
        coin = arguments?.getParcelable(BUNDLE_COIN)
        with(binding) {
            coin?.apply {
                imageCoin.loadImage(image)
                textCoinName.text = name
                textCoinSymbol.text = symbol
            }
        }
    }

    private fun initTabLayout() {
        coin?.let {
            fragments = listOf<Fragment>(
                CoinChartFragment.newInstance(it),
                CoinInfoFragment.newInstance(it.id)
            )
            titles = listOf(getString(R.string.menu_tab_price), getString(R.string.menu_tab_info))
        }
        val adapter = fragments?.let {
            titles?.let { titles ->
                BaseViewPagerAdapter(
                    it,
                    titles,
                    childFragmentManager
                )
            }
        }
        with(binding) {
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    override fun showCoinFavorite(isFavorite: Int) {
        val check = (isFavorite >= 1)
        binding.buttonFavorite.isInvisible = !check
        binding.buttonUnFavorite.isInvisible = check
    }

    override fun isInsertedCoinFavorite(long: Long) {
        context?.showMessage(getString(R.string.msg_insert_success))
    }

    override fun isDeletedCoinFavorite(boolean: Boolean) {
        context?.showMessage(getString(R.string.msg_delete_success))
    }

    override fun showError(error: Exception?) {
        context?.showMessage(error?.message.toString())
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    companion object {
        private const val BUNDLE_COIN = "BUNDLE_COIN"

        fun newInstance(coin: Coin) = CoinDetailFragment().apply {
            arguments = bundleOf(BUNDLE_COIN to coin)
        }
    }
}
