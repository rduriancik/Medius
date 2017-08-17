package com.example.robert.medius.newsFeed.entities

import android.support.annotation.DrawableRes

/**
 * Created by robert on 3.8.2017.
 */

data class News(val id: Long, val createdAt: String, val user: User,
                val newsMedia: NewsMedia, @DrawableRes val socialMediaLogo: Int)

data class NewsMedia(val text: String, val url: String)

data class User(val id: Long, val name: String, val photoUrl: String, val userUrl: String?) //FIXME