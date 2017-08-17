package com.example.robert.medius.login

import com.example.robert.medius.extensions.isLoggedIn
import com.facebook.AccessToken
import com.twitter.sdk.android.core.TwitterCore

/**
 * Created by robert on 2.8.2017.
 */
class LoginInteractorImpl : LoginInteractor {
    override fun isUserLoggedIn() = TwitterCore.getInstance().isLoggedIn() ||
            AccessToken.getCurrentAccessToken() != null
}