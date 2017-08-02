package com.example.robert.medius.loginSettings

import com.example.robert.medius.login.LoginInteractor
import com.example.robert.medius.loginSettings.ui.LoginSettingsView

/**
 * Created by robert on 2.8.2017.
 */
class LoginSettingsPresenterImpl(override var view: LoginSettingsView?, override val interactor: LoginInteractor)
    : LoginSettingsPresenter<LoginSettingsView, LoginInteractor> {
    override fun onBackPressed() {
        if (!interactor.isUserLoggedIn()) {
            view?.navigateToLoginActivity()
        }
    }

    override fun onHomeButtonPressed() {
        if (!interactor.isUserLoggedIn()) {
            view?.navigateToLoginActivity()
        } else {
            view?.navigateToParentActivity()
        }
    }
}