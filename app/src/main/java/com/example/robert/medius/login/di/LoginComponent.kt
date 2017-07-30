package com.example.robert.medius.login.di

import com.example.robert.medius.login.ui.LoginActivity
import dagger.Component

/**
 * Created by robert on 30.7.2017.
 */
@Component(modules = arrayOf(LoginModule::class))
interface LoginComponent {
    fun inject(activity: LoginActivity)
}