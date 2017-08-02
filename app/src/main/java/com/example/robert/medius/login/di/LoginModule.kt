package com.example.robert.medius.login.di

import com.example.robert.medius.login.LoginInteractor
import com.example.robert.medius.login.LoginInteractorImpl
import com.example.robert.medius.login.LoginPresenter
import com.example.robert.medius.login.LoginPresenterImpl
import com.example.robert.medius.login.ui.LoginView
import dagger.Module
import dagger.Provides

/**
 * Created by robert on 2.8.2017.
 */
@Module
class LoginModule(private val view: LoginView) {

    @Provides
    fun provideLoginPresenter(interactor: LoginInteractor): LoginPresenter<LoginView, LoginInteractor>
            = LoginPresenterImpl(view, interactor)

    @Provides
    fun provideLoginInteractor(): LoginInteractor = LoginInteractorImpl()
}