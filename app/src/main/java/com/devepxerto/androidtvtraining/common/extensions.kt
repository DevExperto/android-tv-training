package com.devepxerto.androidtvtraining.common

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build.VERSION.SDK_INT
import android.os.Parcelable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.leanback.widget.DetailsOverviewRow
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.Serializable

fun ImageView.loadUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

fun ImageView.loadDrawable(@DrawableRes drawableRes: Int) {
    Glide.with(this).load(drawableRes).into(this)
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
    SDK_INT >= 33 -> getSerializableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}

fun DetailsOverviewRow.loadImageUrl(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .into<CustomTarget<Drawable>>(object : CustomTarget<Drawable>(176, 313) {
            override fun onResourceReady(
                drawable: Drawable,
                transition: Transition<in Drawable>?
            ) {
                imageDrawable = drawable
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
}