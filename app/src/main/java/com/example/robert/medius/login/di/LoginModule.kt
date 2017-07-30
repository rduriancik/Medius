package com.example.robert.medius.login.di

import com.example.robert.medius.login.LoginInteractor
import com.example.robert.medius.login.LoginInteractorImp
import com.example.robert.medius.login.LoginPresenter
import com.example.robert.medius.login.LoginPresenterImpl
import com.example.robert.medius.login.ui.LoginView
import dagger.Module
import dagger.Provides

/**
 * Created by robert on 30.7.2017.
 */
@Module
class LoginModule(val view: LoginView) {

    @Provides
    fun provideLoginPresenter(interactor: LoginInteractor): LoginPresenter<LoginView>
            = LoginPresenterImpl(view, interactor)

    @Provides
    fun provideLoginView() = view

    @Provides
    fun provideLoginInteractor(): LoginInteractor = LoginInteractorImp()
}