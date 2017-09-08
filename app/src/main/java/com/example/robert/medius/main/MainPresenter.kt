package com.example.robert.medius.main

import com.example.robert.medius.base.Interactor
import com.example.robert.medius.base.Presenter
import com.example.robert.medius.base.View

/**
 * Created by robert on 31.7.2017.
 */
interface MainPresenter<T : View, I : Interactor> : Presenter<T, I> {
    fun onCreate()
}