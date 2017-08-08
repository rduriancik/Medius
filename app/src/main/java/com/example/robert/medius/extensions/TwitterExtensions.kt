package com.example.robert.medius.extensions

import com.twitter.sdk.android.core.TwitterCore

/**
 * Created by robert on 8.8.2017.
 */


fun TwitterCore.isLoggedIn() = sessionManager.activeSession != null

val TwitterCore.userName
    get() = sessionManager.activeSession?.userName