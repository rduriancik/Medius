package com.example.robert.medius.login.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.robert.medius.R
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        switch_facebook.setOnCheckedChangeListener { _, isChecked -> }
        switch_twitter.setCallback(createTwitterCallback())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == switch_twitter.getRequestCode()) {
            switch_twitter.onActivityResult(requestCode, resultCode, data)
        }

        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun setTwitterSwitchChecked() {
        switch_twitter.isChecked = true
    }

    override fun setFacebookSwitchChecked() {
        switch_facebook.isChecked = true
    }

    fun createTwitterCallback(): Callback<TwitterSession> = object : Callback<TwitterSession>() {
        override fun success(result: Result<TwitterSession>?) {
            Snackbar.make(container, "Success", Snackbar.LENGTH_SHORT).show()
        }

        override fun failure(exception: TwitterException?) {
            Snackbar.make(container, "Failure", Snackbar.LENGTH_SHORT).show()
        }
    }
}
