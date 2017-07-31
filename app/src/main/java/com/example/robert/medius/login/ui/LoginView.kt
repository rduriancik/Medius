package com.example.robert.medius.login.ui

import com.example.robert.medius.base.View

/**
 * Created by robert on 20.7.2017.
 */
interface LoginView : View {
    fun setFacebookSwitchChecked()
    fun onError(error: String)
}