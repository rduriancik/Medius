package com.example.robert.medius

/**
 * Created by robert on 1.8.2017.
 */
interface FacebookLoginCallback {
    fun onSuccess()
    fun onCancel();
    fun onError(error: String)
}