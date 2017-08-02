package com.example.robert.medius.login

import com.example.robert.medius.login.ui.LoginView

/**
 * Created by robert on 2.8.2017.
 */
class LoginPresenterImpl(override var view: LoginView?, override val interactor: LoginInteractor)
    : LoginPresenter<LoginView, LoginInteractor> {

    override fun onResume() {
        view?.hideButtons()
        view?.showProgressBar()

        // TODO control login

        view?.hideProgressBar()
        view?.showButtons()
    }
}