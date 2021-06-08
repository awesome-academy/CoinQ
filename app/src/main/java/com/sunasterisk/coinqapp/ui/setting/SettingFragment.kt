package com.sunasterisk.coinqapp.ui.setting

import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.base.BaseFragment
import com.sunasterisk.coinqapp.databinding.FragmentSettingBinding
import com.sunasterisk.coinqapp.ui.calculator.CalculatorFragment
import com.sunasterisk.coinqapp.utils.AppSharedPreferences
import com.sunasterisk.coinqapp.utils.Language.ENGLISH_CODE
import com.sunasterisk.coinqapp.utils.Language.LANGUAGE
import com.sunasterisk.coinqapp.utils.Language.VIETNAMESE_CODE
import com.sunasterisk.coinqapp.utils.replaceFragment
import com.sunasterisk.coinqapp.utils.restart
import com.sunasterisk.coinqapp.utils.setLanguage

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    private var preferences: AppSharedPreferences? = null
    override val binding by lazy { FragmentSettingBinding.inflate(layoutInflater) }

    override fun initViews() {
        preferences = context?.let { AppSharedPreferences.getInstance(it) }
    }

    override fun initListeners() {
        with(binding) {
            imageCalculator.setOnClickListener {
                replaceFragment(
                    parentFragmentManager,
                    R.id.frameContainer,
                    CalculatorFragment()
                )
            }
            buttonEnglish.setOnClickListener {
                activity?.apply {
                    preferences?.putString(LANGUAGE, ENGLISH_CODE)
                    setLanguage(ENGLISH_CODE)
                    restart()
                }
            }
            buttonVietnamese.setOnClickListener {
                activity?.apply {
                    preferences?.putString(LANGUAGE, VIETNAMESE_CODE)
                    setLanguage(VIETNAMESE_CODE)
                    restart()
                }
            }
        }
    }
}
