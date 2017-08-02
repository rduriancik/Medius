package com.example.robert.medius.login

import com.example.robert.medius.base.Interactor
import com.example.robert.medius.base.Presenter
import com.example.robert.medius.base.View

/**
 * Created by robert on 2.8.2017.
 */
interface LoginPresenter<T : View, I : Interactor> : Presenter<T, I> {
    fun onResume()
}