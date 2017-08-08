package com.example.robert.medius.main

import com.example.robert.medius.extensions.isLoggedIn
import com.facebook.AccessToken
import com.twitter.sdk.android.core.TwitterCore

/**
 * Created by robert on 31.7.2017.
 */
class MainInteractorImpl : MainInteractor {
    override fun isTwitterLoggedIn(): Boolean = TwitterCore.getInstance().isLoggedIn()
    override fun isFacebookLoggedIn(): Boolean = AccessToken.getCurrentAccessToken() != null
}