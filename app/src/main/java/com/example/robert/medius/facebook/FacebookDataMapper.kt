package com.example.robert.medius.facebook

import com.example.robert.medius.entities.FacebookNews
import com.example.robert.medius.entities.News
import com.example.robert.medius.entities.NewsMedia
import com.example.robert.medius.newsFeed.types.NewsFeedType
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by robert on 19.8.2017.
 */

fun mapFacebookNewsToNews(facebookNews: List<FacebookNews>): List<News> =
        facebookNews.map { News(it.id, convertDate(it.createdAt), null, mapFacebookMedia(it), NewsFeedType.FACEBOOK) }

private fun mapFacebookMedia(facebookNews: FacebookNews): NewsMedia =
        NewsMedia(facebookNews.message ?: facebookNews.story ?: "Nothing", "")

private fun convertDate(date: String): Long {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
    return formatter.parse(date).time
}