package com.example.youtubeapi.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}
var View.invisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }

fun ImageView.loadImage(url: String){
    Glide.with(context)
        .load(url)
        .into(this)
}