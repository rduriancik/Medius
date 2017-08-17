package com.example.robert.medius

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig


/**
 * Created by robert on 27.7.2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initTwitter()
    }

    private fun initTwitter() {
        val config = TwitterConfig.Builder(this)
                .twitterAuthConfig(TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET))
                .debug(true)
                .logger(DefaultLogger(Log.DEBUG))
                .build()

        Twitter.initialize(config)

        // FIXME
//        val activeSession = TwitterCore.getInstance()
//                .sessionManager.activeSession
//
//        // example of custom OkHttpClient with logging HttpLoggingInterceptor
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val customClient = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor).build()
//
//        // pass custom OkHttpClient into TwitterApiClient and add to TwitterCore
//        val customApiClient: TwitterApiClient
//        if (activeSession != null) {
//            customApiClient = TwitterApiClient(activeSession, customClient)
//            TwitterCore.getInstance().addApiClient(activeSession, customApiClient)
//        } else {
//            customApiClient = TwitterApiClient(customClient)
//            TwitterCore.getInstance().addGuestApiClient(customApiClient)
//        }
    }
}