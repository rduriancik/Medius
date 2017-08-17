package com.example.robert.medius.main.di

import com.example.robert.medius.main.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by robert on 31.7.2017.
 */

@Singleton
@Component(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}