package com.example.robert.medius

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.widget.CompoundButton
import android.widget.Switch
import com.facebook.*
import com.facebook.R
import com.facebook.internal.FragmentWrapper
import com.facebook.internal.LoginAuthorizationType
import com.facebook.internal.Utility
import com.facebook.login.DefaultAudience
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

/**
 * Created by robert on 31.7.2017.
 */
class FacebookLoginSwitch : Switch {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private val loginManager: LoginManager by lazy { LoginManager.getInstance() }
    private val callbackManager: CallbackManager by lazy { CallbackManager.Factory.create() }
    private val properties: LoginSwitchProperties = LoginSwitchProperties()
    private var parentFragment: FragmentWrapper? = null
    private var callback: FacebookLoginCallback? = null
    private val listener = LoginOnCheckedChangeListener()
    private val facebookCallback = createFacebookCallback()

    init {
        if (!isInEditMode && AccessToken.getCurrentAccessToken() != null) {
            this.isChecked = true
        }

        loginManager.registerCallback(callbackManager, facebookCallback)
        super.setOnCheckedChangeListener(listener)
    }

    fun registerCallback(callback: FacebookLoginCallback) {
        this.callback = callback
    }

    fun unregisterCallback() {
        this.callback = null
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Sets the default audience to use when the user logs in.
     * This value is only useful when specifying publish permissions for the native
     * login dialog.
     *
     * @param defaultAudience the default audience value to use
     */
    fun setDefaultAudience(defaultAudience: DefaultAudience) {
        properties.defaultAudience = defaultAudience
    }

    /**
     * Set the permissions to use when the user logs in. The permissions here
     * can only be read permissions. If any publish permissions are included, the login
     * attempt by the user will fail. The LoginButton can only be associated with either
     * read permissions or publish permissions, but not both. Calling both
     * setReadPermissions and setPublishPermissions on the same instance of LoginButton
     * will result in an exception being thrown unless clearPermissions is called in between.
     *
     *
     * This method is only meaningful if called before the user logs in. If this is called
     * after login, and the list of permissions passed in is not a subset
     * of the permissions granted during the authorization, it will log an error.
     *
     *
     * It's important to always pass in a consistent set of permissions to this method, or
     * manage the setting of permissions outside of the LoginButton class altogether
     * (by using the LoginManager explicitly).

     * @param permissions the read permissions to use
     * *
     * @throws UnsupportedOperationException if setPublishPermissions has been called
     */
    fun setReadPermissions(permissions: List<String>) {
        properties.setReadPermissions(permissions)
    }

    /**
     * Set the permissions to use when the user logs in. The permissions here
     * can only be read permissions. If any publish permissions are included, the login
     * attempt by the user will fail. The LoginButton can only be associated with either
     * read permissions or publish permissions, but not both. Calling both
     * setReadPermissions and setPublishPermissions on the same instance of LoginButton
     * will result in an exception being thrown unless clearPermissions is called in between.
     *
     *
     * This method is only meaningful if called before the user logs in. If this is called
     * after login, and the list of permissions passed in is not a subset
     * of the permissions granted during the authorization, it will log an error.
     *
     *
     * It's important to always pass in a consistent set of permissions to this method, or
     * manage the setting of permissions outside of the LoginButton class altogether
     * (by using the LoginManager explicitly).

     * @param permissions the read permissions to use
     * *
     * @throws UnsupportedOperationException if setPublishPermissions has been called
     */
    fun setReadPermissions(vararg permissions: String) {
        properties.setReadPermissions(listOf(*permissions))
    }

    /**
     * Set the permissions to use when the user logs in. The permissions here
     * should only be publish permissions. If any read permissions are included, the login
     * attempt by the user may fail. The LoginButton can only be associated with either
     * read permissions or publish permissions, but not both. Calling both
     * setReadPermissions and setPublishPermissions on the same instance of LoginButton
     * will result in an exception being thrown unless clearPermissions is called in between.
     *
     *
     * This method is only meaningful if called before the user logs in. If this is called
     * after login, and the list of permissions passed in is not a subset
     * of the permissions granted during the authorization, it will log an error.
     *
     *
     * It's important to always pass in a consistent set of permissions to this method, or
     * manage the setting of permissions outside of the LoginButton class altogether
     * (by using the LoginManager explicitly).

     * @param permissions the publish permissions to use
     * *
     * @throws UnsupportedOperationException if setReadPermissions has been called
     * *
     * @throws IllegalArgumentException      if permissions is null or empty
     */
    fun setPublishPermissions(permissions: List<String>) {
        properties.setPublishPermissions(permissions)
    }

    /**
     * Set the permissions to use when the user logs in. The permissions here
     * should only be publish permissions. If any read permissions are included, the login
     * attempt by the user may fail. The LoginButton can only be associated with either
     * read permissions or publish permissions, but not both. Calling both
     * setReadPermissions and setPublishPermissions on the same instance of LoginButton
     * will result in an exception being thrown unless clearPermissions is called in between.
     *
     *
     * This method is only meaningful if called before the user logs in. If this is called
     * after login, and the list of permissions passed in is not a subset
     * of the permissions granted during the authorization, it will log an error.
     *
     *
     * It's important to always pass in a consistent set of permissions to this method, or
     * manage the setting of permissions outside of the LoginButton class altogether
     * (by using the LoginManager explicitly).

     * @param permissions the publish permissions to use
     * *
     * @throws UnsupportedOperationException if setReadPermissions has been called
     * *
     * @throws IllegalArgumentException      if permissions is null or empty
     */
    fun setPublishPermissions(vararg permissions: String) {
        properties.setPublishPermissions(listOf(*permissions))
    }

    /**
     * Sets the login behavior during authorization. If null is specified, the default
     * ([LoginBehavior.NATIVE_WITH_FALLBACK][com.facebook.login.LoginBehavior]
     * will be used.

     * @param loginBehavior The [LoginBehavior][com.facebook.login.LoginBehavior] that
     * *                      specifies what behaviors should be attempted during
     * *                      authorization.
     */
    fun setLoginBehavior(loginBehavior: LoginBehavior) {
        properties.loginBehavior = loginBehavior
    }

    /**
     * Gets the login behavior during authorization. If null is returned, the default
     * ([LoginBehavior.NATIVE_WITH_FALLBACK][com.facebook.login.LoginBehavior]
     * will be used.

     * @return loginBehavior The [LoginBehavior][com.facebook.login.LoginBehavior] that
     * * specifies what behaviors should be attempted during
     * * authorization.
     */
    fun getLoginBehavior(): LoginBehavior {
        return properties.loginBehavior
    }


    /**
     * Clears the permissions currently associated with this LoginButton.
     */
    fun clearPermissions() {
        properties.clearPermissions()
    }

    /**
     * Gets the default audience to use when the user logs in.
     * This value is only useful when specifying publish permissions for the native
     * login dialog.
     *
     * @return the default audience value to use
     */
    fun getDefaultAudience() = properties.defaultAudience

    /**
     * Sets the fragment that contains this control. This allows the button to be embedded inside a
     * Fragment, and will allow the fragment to receive the
     * [onActivityResult][Fragment.onActivityResult]
     * call rather than the Activity.

     * @param fragment the android.support.v4.app.Fragment that contains this control
     */
    fun setFragment(fragment: Fragment) {
        parentFragment = FragmentWrapper(fragment)
    }

    /**
     * Sets the fragment that contains this control. This allows the button to be embedded inside a
     * Fragment, and will allow the fragment to receive the
     * [onActivityResult][Fragment.onActivityResult]
     * call rather than the Activity.

     * @param fragment the android.app.Fragment that contains this control
     */
    fun setFragment(fragment: android.app.Fragment) {
        parentFragment = FragmentWrapper(fragment)
    }

    /**
     * Gets the fragment that contains this control.
     * @return The android.support.v4.app.Fragment that contains this control.
     */
    fun getFragment(): Fragment? =
            if (parentFragment != null) (parentFragment as FragmentWrapper).supportFragment else null

    /**
     * Gets the fragment that contains this control.
     * @return The android.app.Fragment that contains this control.
     */
    fun getNativeFragment(): android.app.Fragment? =
            if (parentFragment != null) (parentFragment as FragmentWrapper).nativeFragment else null

    protected fun getActivity(): Activity {
        var context = context
        while (context !is Activity && context is ContextWrapper) {
            context = context.baseContext
        }

        if (context is Activity) {
            return context
        }

        throw FacebookException("Unable to get Activity.")
    }

    private fun createFacebookCallback(): FacebookCallback<LoginResult> = object : FacebookCallback<LoginResult> {
        override fun onError(error: FacebookException?) {
            toggleSwitch()
            callback?.onError(error.toString())
        }

        override fun onSuccess(result: LoginResult?) {
            callback?.onSuccess()
        }

        override fun onCancel() {
            toggleSwitch()
            callback?.onCancel()
        }

        fun toggleSwitch() {
            this@FacebookLoginSwitch.setOnCheckedChangeListener(null)
            this@FacebookLoginSwitch.toggle()
            this@FacebookLoginSwitch.setOnCheckedChangeListener(listener)
        }
    }

    private class LoginSwitchProperties {
        var defaultAudience = DefaultAudience.FRIENDS
        var permissions: List<String>? = emptyList()
        var authorizationType: LoginAuthorizationType? = null
        var loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK

        fun clearPermissions() {
            permissions = null
            authorizationType = null
        }

        fun setReadPermissions(permissions: List<String>) {
            if (LoginAuthorizationType.PUBLISH == authorizationType) {
                throw UnsupportedOperationException("Cannot call setReadPermissions after " +
                        "setPublishPermissions has been called.")
            }
            this.permissions = permissions
            authorizationType = LoginAuthorizationType.READ
        }

        fun setPublishPermissions(permissions: List<String>) {
            if (LoginAuthorizationType.READ == authorizationType) {
                throw UnsupportedOperationException("Cannot call setPublishPermissions after " +
                        "setReadPermissions has been called.")
            }
            if (Utility.isNullOrEmpty(permissions)) {
                throw IllegalArgumentException(
                        "Permissions for publish actions cannot be null or empty.")
            }
            this.permissions = permissions
            authorizationType = LoginAuthorizationType.PUBLISH
        }
    }

    private inner class LoginOnCheckedChangeListener : OnCheckedChangeListener {
        private val loginManager: LoginManager by lazy {
            val manager = LoginManager.getInstance()
            manager.defaultAudience = properties.defaultAudience
            manager.loginBehavior = properties.loginBehavior
            manager
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            if (isChecked) {
                performLogin()
            } else {
                performLogout(context)
            }
        }

        protected fun performLogin() {
            if (LoginAuthorizationType.PUBLISH == properties.authorizationType) {
                if (getFragment() != null) {
                    loginManager.logInWithPublishPermissions(
                            getFragment(),
                            properties.permissions)
                } else if (getNativeFragment() != null) {
                    loginManager.logInWithPublishPermissions(
                            getNativeFragment(),
                            properties.permissions)
                } else {
                    loginManager.logInWithPublishPermissions(
                            getActivity(),
                            properties.permissions)
                }
            } else {
                if (getFragment() != null) {
                    loginManager.logInWithReadPermissions(
                            getFragment(),
                            properties.permissions)
                } else if (getNativeFragment() != null) {
                    loginManager.logInWithReadPermissions(
                            getNativeFragment(),
                            properties.permissions)
                } else {
                    loginManager.logInWithReadPermissions(
                            getActivity(),
                            properties.permissions)
                }
            }
        }

        protected fun performLogout(context: Context?) {
            val logout = resources.getString(
                    R.string.com_facebook_loginview_log_out_action)
            val cancel = resources.getString(
                    R.string.com_facebook_loginview_cancel_action)
            val message: String
            val profile = Profile.getCurrentProfile()
            if (profile != null && profile.name != null) {
                message = String.format(
                        resources.getString(
                                R.string.com_facebook_loginview_logged_in_as),
                        profile.name)
            } else {
                message = resources.getString(
                        R.string.com_facebook_loginview_logged_in_using_facebook)
            }

            AlertDialog.Builder(context)
                    .setMessage(message)
                    .setCancelable(true)
                    .setPositiveButton(logout) { _, _ -> loginManager.logOut() }
                    .setNegativeButton(cancel, { _, _ -> isChecked = true })
                    .create()
                    .show()
        }
    }
}