package com.example.robert.medius.twitter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.CompoundButton
import android.widget.Switch
import com.example.robert.medius.R
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import com.twitter.sdk.android.core.internal.CommonUtils
import java.lang.ref.WeakReference

/**
 * Created by robert on 28.7.2017.
 */

class TwitterLoginSwitch : Switch {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private val ERROR_MSG_NO_ACTIVITY = "TwitterLoginButton requires an activity." +
            " Override getActivity to provide the activity for this button."

    private var callback: TwitterLoginCallback? = null
    private val twitterCallback = createTwitterCallback()
    private var listener: OnCheckedChangeListener? = LoginOnCheckedChangeListener()
    private val authClient: TwitterAuthClient by lazy {
        TwitterAuthClient()
    }
    private val activityRef: WeakReference<Activity>? by lazy {
        if (getContext() is Activity) {
            WeakReference<Activity>(getContext() as Activity)
        } else if (isInEditMode) {
            null
        } else {
            throw IllegalStateException(ERROR_MSG_NO_ACTIVITY)
        }
    }

    init {
        checkTwitterCoreAndEnable()
        if (TwitterCore.getInstance().isLoggedIn()) {
            isChecked = true
        }
        super.setOnCheckedChangeListener(listener)
    }


    fun setCallback(callback: TwitterLoginCallback?) {
        this.callback = callback
    }

    fun getCallback(): TwitterLoginCallback? {
        return callback
    }

    fun getRequestCode(): Int = authClient.requestCode

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == authClient.requestCode) {
            authClient.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        this.listener = listener
    }

    private fun checkTwitterCoreAndEnable() {
        //Default (Enabled) in edit mode
        if (isInEditMode) return

        try {
            TwitterCore.getInstance()
        } catch (ex: IllegalStateException) {
            //Disable if TwitterCore hasn't started
            Twitter.getLogger().e(TwitterCore.TAG, ex.message)
            isEnabled = false
        }
    }

    private fun createTwitterCallback() = object : Callback<TwitterSession>() {
        override fun success(result: Result<TwitterSession>?) {
            callback?.success()
        }

        override fun failure(exception: TwitterException?) {
            toggleSwitch()
            callback?.failure(exception.toString())
        }
    }

    private fun toggleSwitch() {
        super.setOnCheckedChangeListener(null)
        super.toggle()
        super.setOnCheckedChangeListener(listener)
    }

    private inner class LoginOnCheckedChangeListener : OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            checkActivity(activityRef?.get())
            checkCallback(twitterCallback)

            if (isChecked && !TwitterCore.getInstance().isLoggedIn()) {
                authClient.authorize(activityRef!!.get(), twitterCallback)
            } else {
                createLogoutDialog()
            }
        }

        private fun createLogoutDialog() {
            val logout = resources.getString(
                    R.string.twitter_dialog_logout)
            val cancel = resources.getString(
                    R.string.twitter_dialog_cancel)
            val userName = TwitterCore.getInstance().getUserName()
            val message: String
            if (userName != null) {
                message = String.format(
                        resources.getString(R.string.twitter_dialog_message_logged_in_as), userName)
            } else {
                message = resources.getString(R.string.twitter_dialog_message_logged_using)
            }

            AlertDialog.Builder(context)
                    .setMessage(message)
                    .setCancelable(true)
                    .setPositiveButton(logout) { _, _ ->
                        TwitterCore.getInstance().sessionManager.clearActiveSession()
                    }
                    .setNegativeButton(cancel) { _, _ -> toggleSwitch() }
                    .setOnCancelListener { toggleSwitch() }
                    .create()
                    .show()
        }

        private fun checkCallback(callback: Callback<*>?) {
            if (callback == null) {
                CommonUtils.logOrThrowIllegalStateException(TwitterCore.TAG,
                        "Callback must not be null, did you call setCallback?")
            }
        }

        private fun checkActivity(activity: Activity?) {
            if (activity == null || activity.isFinishing) {
                CommonUtils.logOrThrowIllegalStateException(TwitterCore.TAG,
                        ERROR_MSG_NO_ACTIVITY)
            }
        }
    }
}