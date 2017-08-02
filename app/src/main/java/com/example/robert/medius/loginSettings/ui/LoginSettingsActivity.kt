package com.example.robert.medius.loginSettings.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.robert.medius.R
import com.example.robert.medius.extensions.snackbar
import com.example.robert.medius.facebook.FacebookLoginCallback
import com.example.robert.medius.login.ui.LoginActivity
import com.example.robert.medius.twitter.TwitterLoginCallback
import kotlinx.android.synthetic.main.activity_login_settings.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast

class LoginSettingsActivity : AppCompatActivity(), LoginSettingsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_settings)

        setupSwitches()
    }

    override fun onDestroy() {
        // TODO presenter.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == switch_twitter.getRequestCode()) {
            switch_twitter.onActivityResult(requestCode, resultCode, data)
        } else {
            switch_facebook.onActivityResult(requestCode, resultCode, data)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun navigateToLoginActivity() {
        startActivity(intentFor<LoginActivity>()
                .clearTask()
                .newTask())
    }

    private fun setupSwitches() {
        switch_facebook.registerCallback(createFacebookCallback())
        switch_twitter.setCallback(createTwitterCallback())
    }

    fun createTwitterCallback(): TwitterLoginCallback = object : TwitterLoginCallback {
        override fun success() {
            snackbar(container, "Success")
        }

        override fun failure(error: String) {
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
