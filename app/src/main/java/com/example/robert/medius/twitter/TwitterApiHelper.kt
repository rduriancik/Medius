package com.example.robert.medius.twitter

import com.example.robert.medius.entities.News
import com.example.robert.medius.libs.base.EventBus
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
    fun loadMoreTimeline(maxId: Long? = null) {
        getTimeline(maxId = maxId) {
            news, error ->
            eventBus.post(NewsFeedEvent.LoadMoreEvent(news, error, NewsFeedType.TWITTER))
        }
    }

    fun refreshTimeline() {
        getTimeline { news, error -> eventBus.post(NewsFeedEvent.RefreshEvent(news, error, NewsFeedType.TWITTER)) }
    }

    private fun getTimeline(count: Int = 50,
                            sinceId: Long? = null,
                            maxId: Long? = null,
                            post: (news: List<News>?, error: String?) -> Unit) {
        statusesService
                .homeTimeline(count, sinceId, maxId, false, true, false, true)
                .enqueue(object : Callback<MutableList<Tweet>>() {
                    override fun success(result: Result<MutableList<Tweet>>) {
                        val items = result.data
                        if (items.isNotEmpty()) {
                            post(mapTweetsToNews(items), null)
                        } else {
                            post(null, "No items")
                        }
                    }

                    override fun failure(exception: TwitterException?) {
                        post(null, exception.toString())
                    }
                })
    }

}
