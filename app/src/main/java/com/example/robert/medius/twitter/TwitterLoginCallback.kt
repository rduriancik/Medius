package com.example.robert.medius.twitter

/**
 * Created by robert on 1.8.2017.
 */
interface TwitterLoginCallback {
    fun success()
    fun failure(error: String)
}