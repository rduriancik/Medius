package com.example.robert.medius.login

import com.example.robert.medius.base.Presenter
import com.example.robert.medius.base.View

/**
 * Created by robert on 20.7.2017.
 */
interface LoginPresenter<T : View> : Presenter<T> {
    fun onResume();
}