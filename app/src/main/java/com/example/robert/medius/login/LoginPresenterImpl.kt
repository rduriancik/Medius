package com.example.robert.medius.login

import com.example.robert.medius.login.ui.LoginView

/**
 * Created by robert on 2.8.2017.
 */
class LoginPresenterImpl(override var view: LoginView?, override val interactor: LoginInteractor)
    : LoginPresenter<LoginView, LoginInteractor> {

    override fun onResume() {
        view?.postDelay({
            view?.hideButtons()
            view?.showProgressBar()

            if (interactor.isUserLoggedIn()) {
                view?.navigateToMainActivity()
            } else {
                view?.hideProgressBar()
                view?.showButtons()
            }
        }, 2000)
    }
}