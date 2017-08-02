package com.example.robert.medius.loginSettings

import com.example.robert.medius.login.LoginInteractor
import com.example.robert.medius.loginSettings.ui.LoginSettingsView

/**
 * Created by robert on 2.8.2017.
 */
class LoginSettingsPresenterImpl(override var view: LoginSettingsView?, override val interactor: LoginInteractor)
    : LoginSettingsPresenter<LoginSettingsView, LoginInteractor> {
    override fun onResume() {
        if (!interactor.isUserLoggedIn()) {
            view?.navigateToLoginActivity()
        }
    }
}