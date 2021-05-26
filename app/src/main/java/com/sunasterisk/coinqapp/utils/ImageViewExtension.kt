package com.sunasterisk.coinqapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sunasterisk.coinqapp.R

fun ImageView.loadImage(url : String){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_favorite)
        .into(this)
}
