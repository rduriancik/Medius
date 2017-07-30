package com.example.robert.medius.base

/**
 * Created by robert on 27.7.2017.
 */
interface Presenter<T : View> {
    var view: T?

    fun onDestroy() {
        view = null
    }
}