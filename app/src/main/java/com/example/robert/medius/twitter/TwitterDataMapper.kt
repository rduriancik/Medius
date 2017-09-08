package com.example.robert.medius.twitter

import com.example.robert.medius.entities.News
import com.example.robert.medius.entities.NewsMedia
import com.example.robert.medius.entities.User
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.core.models.UrlEntity
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
        NewsMedia(parseTweetText(tweet), createUrlIndicesList(tweet.entities.urls, tweet.displayTextRange[1]))

private fun convertDate(date: String): Long {
    val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
    return formatter.parse(date).time
}

private fun createTweetUrl(id: String): String = "https://twitter.com/null/status/$id"

private fun createTwitterUserPhotoUrl(url: String) = url.replace("_normal.", "_200x200.")

private fun parseTweetText(tweet: Tweet): String = with(tweet) {
    var parsed = text.take(displayTextRange[1])
    entities.urls.forEach {
        if (it.indices[1] <= parsed.length) {
            parsed = parsed.replaceRange(it.indices[0], it.indices[1], it.displayUrl)
        }
    }

    return parsed
}

private fun createUrlIndicesList(urls: MutableList<UrlEntity>, maxLength: Int): List<Pair<Int, Int>>? {
    if (urls.isNotEmpty()) {
        val indices: MutableList<Pair<Int, Int>> = mutableListOf()
        urls.filter { it.indices[1] <= maxLength }
                .forEach { indices.add(it.indices[0] to it.indices[1]) }

        return indices
    }

    return null
}
