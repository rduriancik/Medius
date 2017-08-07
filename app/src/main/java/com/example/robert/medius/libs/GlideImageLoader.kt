package com.example.robert.medius.libs

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.example.robert.medius.libs.base.ImageLoader

/**
 * Created by robert on 7.8.2017.
 */
class GlideImageLoader(val requestManager: RequestManager) : ImageLoader {
    override fun load(url: String, imageView: ImageView) {
        requestManager.load(url).into(imageView)
    }
}