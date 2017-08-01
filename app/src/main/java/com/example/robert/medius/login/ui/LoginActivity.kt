package com.example.robert.medius.login.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.robert.medius.FacebookLoginCallback
import com.example.robert.medius.R
import com.example.robert.medius.extensions.snackbar
import com.example.robert.medius.login.LoginPresenter
import com.example.robert.medius.login.di.DaggerLoginComponent
import com.example.robert.medius.login.di.LoginModule
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {

    @Inject lateinit var presenter: LoginPresenter<LoginView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupInjection()
        setupSwitches()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == switch_twitter.getRequestCode()) {
            switch_twitter.onActivityResult(requestCode, resultCode, data)
        } else {
            switch_facebook.onActivityResult(requestCode, resultCode, data)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onError(error: String) {
        snackbar(container, error)
    }

    private fun setupInjection() {
        DaggerLoginComponent.builder()
                .loginModule(LoginModule(this))
                .build()
                .inject(this)
    }

    private fun setupSwitches() {
        switch_facebook.registerCallback(createFacebookCallback())
        switch_twitter.setCallback(createTwitterCallback())
    }

    fun createTwitterCallback(): Callback<TwitterSession> = object : Callback<TwitterSession>() {
        override fun success(result: Result<TwitterSession>?) {
            snackbar(container, "Success")
        }

        override fun failure(exception: TwitterException?) {
            snackbar(container, "Failure")
        }
    }

    fun createFacebookCallback(): FacebookLoginCallback = object : FacebookLoginCallback {
        override fun onError(error: String) {
            toast("error ${error}")
        }

        override fun onSuccess() {
            toast("Success")
        }

        override fun onCancel() {
            toast("cancel")
        }
    }
}
