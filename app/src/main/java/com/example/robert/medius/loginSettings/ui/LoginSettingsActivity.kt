package com.example.robert.medius.loginSettings.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.robert.medius.R
import com.example.robert.medius.extensions.snackbar
import com.example.robert.medius.facebook.FacebookLoginCallback
import com.example.robert.medius.login.LoginInteractor
import com.example.robert.medius.login.ui.LoginActivity
import com.example.robert.medius.loginSettings.LoginSettingsPresenter
import com.example.robert.medius.loginSettings.di.DaggerLoginSettingsComponent
import com.example.robert.medius.loginSettings.di.LoginSettingsComponent
import com.example.robert.medius.loginSettings.di.LoginSettingsModule
import com.example.robert.medius.twitter.TwitterLoginCallback
import kotlinx.android.synthetic.main.activity_login_settings.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast
import javax.inject.Inject

class LoginSettingsActivity : AppCompatActivity(), LoginSettingsView {

    val loginSettingsComponent: LoginSettingsComponent by lazy {
        DaggerLoginSettingsComponent.builder()
                .loginSettingsModule(LoginSettingsModule(this))
                .build()
    }
    @Inject lateinit var presenter: LoginSettingsPresenter<LoginSettingsView, LoginInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_settings)

        loginSettingsComponent.inject(this)
        setupSwitches()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            presenter.onHomeButtonPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
        super.onBackPressed()
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

    override fun navigateToParentActivity() {
        NavUtils.navigateUpFromSameTask(this)
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
