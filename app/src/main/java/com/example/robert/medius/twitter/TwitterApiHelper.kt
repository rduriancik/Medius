package com.example.robert.medius.twitter

import com.example.robert.medius.libs.base.EventBus
import com.example.robert.medius.newsFeed.entities.News
import com.example.robert.medius.newsFeed.events.NewsFeedEvent
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.core.services.StatusesService

/**
 * Created by robert on 28.7.2017.
 */

class TwitterApiHelper(val statusesService: StatusesService, val eventBus: EventBus) {

    fun getTimeline(count: Int = 50, sinceId: Long? = null, maxId: Long? = null) {
        statusesService
                .homeTimeline(count, sinceId, maxId, false, true, false, true)
                .enqueue(object : Callback<MutableList<Tweet>>() {
                    override fun success(result: Result<MutableList<Tweet>>) {
                        val items = result.data
                        if (items.isNotEmpty()) {
                            post(mapTweetsToNews(items))
                        } else {
                            post(error = "No items")
                        }
                    }

                    override fun failure(exception: TwitterException?) {
                        post(error = exception.toString())
                    }
                })
    }

    fun post(news: List<News>? = null, error: String? = null) {
        eventBus.post(NewsFeedEvent(news, error, NewsFeedType.TWITTER))
    }
}
