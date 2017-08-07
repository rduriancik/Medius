package com.example.robert.medius.loginSettings.di

import com.example.robert.medius.loginSettings.ui.LoginSettingsActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by robert on 2.8.2017.
 */
@Singleton
@Component(modules = arrayOf(LoginSettingsModule::class))
interface LoginSettingsComponent {
    fun inject(loginSettingsActivity: LoginSettingsActivity)
}