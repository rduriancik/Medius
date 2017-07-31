package com.example.robert.medius.main.di

import com.example.robert.medius.main.ui.MainActivity
import dagger.Component

/**
 * Created by robert on 31.7.2017.
 */

@Component(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}