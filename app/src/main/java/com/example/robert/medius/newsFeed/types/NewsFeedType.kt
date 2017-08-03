package com.example.robert.medius.newsFeed.types

import android.support.annotation.ColorRes
import com.example.robert.medius.R

/**
 * Created by robert on 31.7.2017.
 */
enum class NewsFeedType(@ColorRes val color: Int) {
    NONE(R.color.colorAccent),
    FACEBOOK(R.color.facebookColor),
    TWITTER(R.color.twitterColor)
}