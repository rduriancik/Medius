package com.example.robert.medius.login

import com.example.robert.medius.login.ui.LoginView

/**
 * Created by robert on 28.7.2017.
 */
class LoginPresenterImpl(override var view: LoginView?, val interactor: LoginInteractor) : LoginPresenter<LoginView> {

    override fun onResume() {

    }
}