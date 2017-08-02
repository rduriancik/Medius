package com.example.robert.medius.base

/**
 * Created by robert on 27.7.2017.
 */
interface Presenter<T : View, I : Interactor> {
    var view: T?
    val interactor: I

    fun onDestroy() {
        view = null
    }
}