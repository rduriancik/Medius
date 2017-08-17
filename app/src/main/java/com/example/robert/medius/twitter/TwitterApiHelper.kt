package com.example.robert.medius.twitter

import com.example.robert.medius.libs.base.EventBus
import com.example.robert.medius.newsFeed.entities.News
import com.example.robert.medius.newsFeed.events.InitTimelineEvent
import com.example.robert.medius.newsFeed.events.LoadMoreEvent
import com.example.robert.medius.newsFeed.events.RefreshEvent
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

    fun initTimeline() {
        getTimeline { news, error -> eventBus.post(InitTimelineEvent(news, error, NewsFeedType.TWITTER)) }
    }

    fun loadMoreTimeline(sinceId: Long? = null,
                         maxId: Long? = null) {
        getTimeline(sinceId = sinceId, maxId = maxId) {
            news, error ->
            eventBus.post(LoadMoreEvent(news, error, NewsFeedType.TWITTER))
        }
    }

    fun refreshTimeline() {
        getTimeline { news, error -> eventBus.post(RefreshEvent(news, error, NewsFeedType.TWITTER)) }
    }

    // TODO check kotlin cost - inline
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
