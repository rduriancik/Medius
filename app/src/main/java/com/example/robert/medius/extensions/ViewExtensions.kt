package com.example.robert.medius.extensions

import android.app.Activity
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.view.View

/**
 * Created by robert on 30.7.2017.
 */

fun Activity.snackbar(view: View, text: CharSequence) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
}

fun TabLayout.setSelectedTabTextColor(color: Int) {
    setTabTextColors(Color.GRAY, color)
}