package com.example.robert.medius.facebook

import com.example.robert.medius.entities.FacebookNews
import com.example.robert.medius.entities.News
import com.example.robert.medius.libs.base.EventBus
import com.example.robert.medius.newsFeed.events.NewsFeedEvent
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.google.gson.Gson

/**
 * Created by robert on 17.8.2017.
 */

class FacebookApiHelper(val eventBus: EventBus) {
    private val gson = Gson()
    private var request: GraphRequest? = GraphRequest.newGraphPathRequest(
            AccessToken.getCurrentAccessToken(),
            "/me/feed",
            null
    )

    fun loadMoreTimeline() {
        if (request != null) {
            request!!.callback = createCallback { news, error -> eventBus.post(NewsFeedEvent.LoadMoreEvent(news, error, NewsFeedType.FACEBOOK)) }
            request!!.executeAsync()
        } else {
            eventBus.post(NewsFeedEvent.LoadMoreEvent(null, "No more items", NewsFeedType.FACEBOOK))
        }
    }

    fun refreshTimeline() {
        request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                createCallback { news, error -> eventBus.post(NewsFeedEvent.RefreshEvent(news, error, NewsFeedType.FACEBOOK)) }
        )
        request?.executeAsync()
    }

    private fun createCallback(post: (news: List<News>?, error: String?) -> Unit) =
            GraphRequest.Callback { response ->
                val result: List<FacebookNews>? = gson.fromJson(response.jsonObject.getJSONArray("data").toString(), Array<FacebookNews>::class.java)?.toList()
                if (result != null) {
                    post(mapFacebookNewsToNews(result), null)
                } else {
                    post(null, "Error")
                }

                request = response.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT)
            }
}