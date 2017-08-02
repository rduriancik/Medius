package com.example.robert.medius.main

import com.example.robert.medius.base.Interactor

/**
 * Created by robert on 31.7.2017.
 */
interface MainInteractor : Interactor {
    fun isTwitterLoggedIn(): Boolean
    fun isFacebookLoggedIn(): Boolean
}