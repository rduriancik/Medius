package com.example.robert.medius.facebook

import com.example.robert.medius.entities.FacebookNews
import com.example.robert.medius.entities.News
import com.example.robert.medius.entities.NewsMedia
import com.example.robert.medius.newsFeed.types.NewsFeedType

/**
 * Created by robert on 19.8.2017.
 */

fun mapFacebookNewsToNews(facebookNews: List<FacebookNews>): List<News> =
        facebookNews.map { News(it.id, it.createdAt, null, mapFacebookMedia(it), NewsFeedType.FACEBOOK) }

private fun mapFacebookMedia(facebookNews: FacebookNews): NewsMedia =
        NewsMedia(facebookNews.message ?: facebookNews.story ?: "Nothing", "")