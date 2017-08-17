package com.example.robert.medius.newsFeed.types

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import com.example.robert.medius.R

/**
 * Created by robert on 31.7.2017.
 */
enum class NewsFeedType(@ColorRes val color: Int, @DrawableRes val logo: Int) {
    NONE(R.color.colorAccent, 0),
    FACEBOOK(R.color.facebookColor, R.drawable.facebook_logo),
    TWITTER(R.color.twitterColor, R.drawable.twitter_logo)
}