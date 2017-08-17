package com.example.robert.medius.twitter

import com.example.robert.medius.entities.News
import com.example.robert.medius.entities.NewsMedia
import com.example.robert.medius.entities.User
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.core.models.User as TwitterUser

/**
 * Created by robert on 8.8.2017.
 */

fun mapTweetsToNews(tweets: List<Tweet>)
        = tweets.map { News(it.id, it.createdAt, mapTwitterUser(it.user), mapTwitterMedia(it), NewsFeedType.TWITTER) }


fun mapTwitterUser(user: TwitterUser)
        = User(user.id, user.name, user.profileImageUrlHttps, user.url)

fun mapTwitterMedia(tweet: Tweet) =
        NewsMedia(tweet.text, tweet.source)