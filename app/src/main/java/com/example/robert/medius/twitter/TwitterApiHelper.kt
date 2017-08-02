package com.example.robert.medius.twitter

import com.twitter.sdk.android.core.TwitterCore

/**
 * Created by robert on 28.7.2017.
 */

fun TwitterCore.isLoggedIn() = sessionManager.activeSession != null

val TwitterCore.userName
    get() = sessionManager.activeSession?.userName