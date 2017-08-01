package com.example.robert.medius.twitter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.CompoundButton
import android.widget.Switch
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
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

    init {
        checkTwitterCoreAndEnable()
        if (TwitterCore.getInstance().isLoggedIn()) {
            isChecked = true
        }
        super.setOnCheckedChangeListener(LoginOnCheckedChangeListener())
    }

    private val ERROR_MSG_NO_ACTIVITY = "TwitterLoginButton requires an activity." +
            " Override getActivity to provide the activity for this button."

    private val activityRef: WeakReference<Activity>? by lazy {
        if (getContext() is Activity) {
            WeakReference<Activity>(getContext() as Activity)
        } else if (isInEditMode) {
            null
        } else {
            throw IllegalStateException(ERROR_MSG_NO_ACTIVITY)
        }
    }
    private var callback: Callback<TwitterSession>? = null
    private val authClient: TwitterAuthClient by lazy {
        TwitterAuthClient()
    }

    fun setCallback(callback: Callback<TwitterSession>?) {
        if (callback == null) {
            throw IllegalArgumentException("Callback cannot be null")
        }
        this.callback = callback
    }

    fun getCallback(): Callback<TwitterSession>? {
        return callback
    }

    fun getRequestCode(): Int = authClient.requestCode

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == authClient.requestCode) {
            authClient.onActivityResult(requestCode, resultCode, data)
        }
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

    private inner class LoginOnCheckedChangeListener : OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            checkActivity(activityRef?.get())
            checkCallback(callback)

            if (isChecked && !TwitterCore.getInstance().isLoggedIn()) {
                authClient.authorize(activityRef!!.get(), callback)
            } else {
                // TODO alertdialog like in the facebookloginswitch
                TwitterCore.getInstance().sessionManager.clearActiveSession()
            }
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