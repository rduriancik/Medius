package com.example.robert.medius

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_social_media.*

class SocialMediaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_media)

        switch_facebook.setOnCheckedChangeListener { _, isChecked -> /*TODO if (isChecked)  sign in else  sign of*/ }
        switch_twitter.setOnCheckedChangeListener { _, isChecked -> /*TODO if (isChecked)  sign in else  sign of*/ }
    }


}
