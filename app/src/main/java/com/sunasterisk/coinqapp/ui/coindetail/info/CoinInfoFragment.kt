package com.sunasterisk.coinqapp.ui.coindetail.info

import android.os.Handler
import android.os.Looper
import androidx.core.os.bundleOf
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.data.model.CoinDetail
import com.sunasterisk.coinqapp.data.repository.CoinRepository
import com.sunasterisk.coinqapp.data.source.local.CoinLocalDataSource
import com.sunasterisk.coinqapp.data.source.local.dao.CoinDaoImpl
import com.sunasterisk.coinqapp.data.source.local.db.AppDataBase
import com.sunasterisk.coinqapp.data.source.remote.CoinRemoteDataSource
import com.sunasterisk.coinqapp.databinding.FragmentCoinInfoBinding
import com.sunasterisk.coinqapp.utils.CustomProgressBar
import com.sunasterisk.coinqapp.utils.showMessage

class CoinInfoFragment : BaseFragment<FragmentCoinInfoBinding>(), CoinInfoContract.View {

    private var presenter: CoinInfoContract.Presenter? = null
    private val loadingProgressBar = CustomProgressBar()

    override val binding by lazy { FragmentCoinInfoBinding.inflate(layoutInflater) }

    override fun initViews() {
        val coinId = arguments?.getString(BUNDLE_COIN_ID)
        presenter =
            CoinInfoPresenter(
                this,
                CoinRepository.getInstance(
                    CoinRemoteDataSource.getInstance(), CoinLocalDataSource.getInstance(
                        CoinDaoImpl.getInstance(
                            AppDataBase.getInstance(context)
                        )
                    )
                )
            )
        coinId?.let { presenter?.getCoinDetail(it) }
    }

    override fun initListeners() {
    }

    override fun showCoinDetail(coinDetail: CoinDetail) {
        with(binding) {
            coinDetail.apply {
                textHomePageURL.text = homePage
                textBlockChainURL.text = blockChainSite
                textForumURL.text = forumSite
                textDescription.text = description.replace(Regex(HTML_REGEX), EMPTY_STRING)
            }
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
        Handler(Looper.myLooper()!!).postDelayed({
            loadingProgressBar.dialog.dismiss()
        }, CustomProgressBar.DIALOG_MIN_TIME)
    }

    companion object {
        private const val BUNDLE_COIN_ID = "BUNDLE_COIN_ID"
        private const val HTML_REGEX = "<.*?>"
        private const val EMPTY_STRING = ""

        fun newInstance(coinId: String) = CoinInfoFragment().apply {
            arguments = bundleOf(BUNDLE_COIN_ID to coinId)
        }
    }
}
