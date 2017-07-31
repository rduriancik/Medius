package com.example.robert.medius.extensions

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by robert on 30.7.2017.
 */

fun Activity.snackbar(view: View, text: CharSequence) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
}