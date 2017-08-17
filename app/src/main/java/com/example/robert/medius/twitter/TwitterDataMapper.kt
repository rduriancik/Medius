package com.example.robert.medius.twitter

import com.example.robert.medius.R
import com.example.robert.medius.newsFeed.entities.News
import com.example.robert.medius.newsFeed.entities.NewsMedia
import com.example.robert.medius.newsFeed.entities.User
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.core.models.User as TwitterUser

/**
 * Created by robert on 8.8.2017.
 */

fun mapTweetsToNews(tweets: List<Tweet>)
        = tweets.map { News(it.id, it.createdAt, mapTwitterUser(it.user), mapTwitterMedia(it), R.drawable.twitter_logo) }


fun mapTwitterUser(user: TwitterUser)
        = User(user.id, user.name, user.profileImageUrlHttps, user.url)

fun mapTwitterMedia(tweet: Tweet) =
        NewsMedia(tweet.text, tweet.source)