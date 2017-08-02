package com.example.robert.medius.loginSettings.di

import com.example.robert.medius.login.LoginInteractor
import com.example.robert.medius.login.LoginInteractorImpl
import com.example.robert.medius.loginSettings.LoginSettingsPresenter
import com.example.robert.medius.loginSettings.LoginSettingsPresenterImpl
import com.example.robert.medius.loginSettings.ui.LoginSettingsView
import dagger.Module
import dagger.Provides

/**
 * Created by robert on 2.8.2017.
 */
@Module
class LoginSettingsModule(private val view: LoginSettingsView) {

    @Provides
    fun provideLoginSettingsPresenter(interactor: LoginInteractor): LoginSettingsPresenter<LoginSettingsView, LoginInteractor>
            = LoginSettingsPresenterImpl(view, interactor)

    @Provides
    fun provideLoginInteractor(): LoginInteractor = LoginInteractorImpl()
}