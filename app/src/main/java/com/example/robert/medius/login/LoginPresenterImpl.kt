package com.example.robert.medius.login

import com.example.robert.medius.login.ui.LoginView
import com.example.robert.medius.twitter.isLoggedIn
import com.twitter.sdk.android.core.TwitterCore

/**
 * Created by robert on 28.7.2017.
 */
class LoginPresenterImpl(override var view: LoginView?, val interactor: LoginInteractor) : LoginPresenter<LoginView> {

    override fun onResume() {
        view?.onError("${TwitterCore.getInstance().isLoggedIn()}")
        if (interactor.isTwitterLoggedIn()) {
            view?.setTwitterSwitchChecked()
        }
    }
}