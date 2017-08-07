package com.example.robert.medius.login.di

import com.example.robert.medius.login.ui.LoginActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by robert on 2.8.2017.
 */
@Singleton
@Component(modules = arrayOf(LoginModule::class))
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}