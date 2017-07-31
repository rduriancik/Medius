package com.example.robert.medius.login

import com.example.robert.medius.twitter.isLoggedIn
import com.twitter.sdk.android.core.TwitterCore

/**
 * Created by robert on 30.7.2017.
 */
class LoginInteractorImp : LoginInteractor {
    override fun isTwitterLoggedIn(): Boolean = TwitterCore.getInstance().isLoggedIn()
}