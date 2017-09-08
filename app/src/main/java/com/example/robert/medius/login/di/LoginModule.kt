package com.example.robert.medius.login.di

import android.os.Handler
import com.example.robert.medius.login.LoginInteractor
import com.example.robert.medius.login.LoginInteractorImpl
import com.example.robert.medius.login.LoginPresenter
import com.example.robert.medius.login.LoginPresenterImpl
import com.example.robert.medius.login.ui.LoginView
import com.facebook.CallbackManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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

    @Provides
    @Singleton
    fun provideCallbackManager(): CallbackManager = CallbackManager.Factory.create()

    @Provides
    fun provideHandler() = Handler()

}