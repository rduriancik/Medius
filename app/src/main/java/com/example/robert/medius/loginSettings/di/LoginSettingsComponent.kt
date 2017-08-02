package com.example.robert.medius.loginSettings.di

import com.example.robert.medius.loginSettings.ui.LoginSettingsActivity
import dagger.Component

/**
 * Created by robert on 2.8.2017.
 */
@Component(modules = arrayOf(LoginSettingsModule::class))
interface LoginSettingsComponent {
    fun inject(loginSettingsActivity: LoginSettingsActivity)
}