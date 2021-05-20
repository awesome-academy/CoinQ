package com.sunasterisk.coinqapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.sunasterisk.coinqapp.R

abstract class BaseActivity<T : ViewBinding>() : AppCompatActivity() {

    protected abstract val binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        createView()
    }

    protected abstract fun createView()

}
