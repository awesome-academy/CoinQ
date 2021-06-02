package com.sunasterisk.coinqapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.res.ResourcesCompat
import com.sunasterisk.coinqapp.R
import com.sunasterisk.coinqapp.databinding.CustomProgressBarBinding

class CustomProgressBar {
    lateinit var dialog: CustomDialog

    @SuppressLint("ResourceAsColor")
    fun showProgressBar(context: Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        val binding = CustomProgressBarBinding.inflate(inflater)
        if (title != null) {
            binding.textMessage.text = title
        }
        binding.cardViewCustom.setCardBackgroundColor(R.color.color_panache)
        setColorFilter(
            binding.progressBarLoading.indeterminateDrawable,
            ResourcesCompat.getColor(context.resources, R.color.color_panache, null)
        )
        binding.textMessage.setTextColor(Color.WHITE)
        dialog = CustomDialog(context)
        dialog.apply {
            setContentView(binding.root)
            show()
        }
        return dialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            window?.let { it.decorView.rootView?.setBackgroundResource(R.color.color_panache) }
            window?.let {
                it.decorView.setOnApplyWindowInsetsListener { _, insets ->
                    insets.consumeSystemWindowInsets()
                }
            }
        }
    }

    companion object {
        const val DIALOG_MIN_TIME: Long = 250
    }
}
