package com.example.robert.medius.login.ui

import com.example.robert.medius.base.View

/**
 * Created by robert on 2.8.2017.
 */
interface LoginView : View {
    fun navigateToMainActivity()
    fun showProgressBar()
    fun hideProgressBar()
    fun showButtons()
    fun hideButtons()
    fun postDelay(task: () -> Unit, delay: Long)
}