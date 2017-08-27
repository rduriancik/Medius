package com.example.robert.medius.twitter

import com.example.robert.medius.entities.News
import com.example.robert.medius.entities.NewsMedia
import com.example.robert.medius.entities.User
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.twitter.sdk.android.core.models.Tweet
import java.text.SimpleDateFormat
import java.util.*
import com.twitter.sdk.android.core.models.User as TwitterUser

/**
 * Created by robert on 8.8.2017.
 */

fun mapTweetsToNews(tweets: List<Tweet>)
        = tweets.map { News(it.id.toString(), convertDate(it.createdAt), createTweetUrl(it.idStr), mapTwitterUser(it.user), mapTwitterMedia(it), NewsFeedType.TWITTER) }


private fun mapTwitterUser(user: TwitterUser)
        = User(user.id, user.name, createTwitterUserPhotoUrl(user.profileImageUrlHttps), "https://twitter.com/${user.screenName}")

private fun mapTwitterMedia(tweet: Tweet) =
        NewsMedia(tweet.text, tweet.source)

private fun convertDate(date: String): Long {
    val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
    return formatter.parse(date).time
}

private fun createTweetUrl(id: String): String = "https://twitter.com/null/status/$id"

private fun createTwitterUserPhotoUrl(url: String) = url.replace("_normal.", "_200x200.")