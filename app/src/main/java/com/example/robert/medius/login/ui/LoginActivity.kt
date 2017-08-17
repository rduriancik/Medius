package com.example.robert.medius.login.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.robert.medius.R
import com.example.robert.medius.login.LoginInteractor
import com.example.robert.medius.login.LoginPresenter
import com.example.robert.medius.login.di.DaggerLoginComponent
import com.example.robert.medius.login.di.LoginComponent
import com.example.robert.medius.login.di.LoginModule
import com.example.robert.medius.main.ui.MainActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {

    val loginComponent: LoginComponent by lazy {
        DaggerLoginComponent.builder()
                .loginModule(LoginModule(this))
                .build()
    }
    @Inject lateinit var presenter: LoginPresenter<LoginView, LoginInteractor>
    private val facebookCallbackManager: CallbackManager = CallbackManager.Factory.create()
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginComponent.inject(this)
        setupButtons()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        button_twitter.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun showButtons() {
        button_facebook.visibility = View.VISIBLE
        button_twitter.visibility = View.VISIBLE
    }

    override fun hideButtons() {
        button_facebook.visibility = View.GONE
        button_twitter.visibility = View.GONE
    }

    override fun postDelay(task: () -> Unit, delay: Long) {
        handler.postDelayed({ task() }, delay)
    }

    override fun navigateToMainActivity() {
        startActivity(intentFor<MainActivity>()
                .newTask()
                .clearTask())
    }

    private fun setupButtons() {
        button_facebook.setReadPermissions("user_posts")
        button_facebook.registerCallback(facebookCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                navigateToMainActivity()
            }

            override fun onError(error: FacebookException?) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancel() {
                //To change body of created functions use File | Settings | File Templates.
            }
        })

        button_twitter.callback = object : Callback<TwitterSession>() {
            override fun failure(exception: TwitterException?) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun success(result: Result<TwitterSession>?) {
                navigateToMainActivity()
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            handler.postDelayed({
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            }, 200)
        }
    }
}
