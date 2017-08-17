package com.example.robert.medius.facebook

import android.util.Log
import com.example.robert.medius.libs.base.EventBus
import com.example.robert.medius.newsFeed.events.NewsFeedEvent
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse

/**
 * Created by robert on 17.8.2017.
 */

class FacebookApiHelper(val eventBus: EventBus) {
    private var request: GraphRequest? = GraphRequest.newGraphPathRequest(
            AccessToken.getCurrentAccessToken(),
            "/me/feed",
            { response ->
                Log.d("RESPONSE", response.rawResponse)
                // TODO map
                request = response.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT)
            }
    )

    fun loadMoreTimeline() {
        request?.executeAsync() ?: eventBus.post(NewsFeedEvent.LoadMoreEvent(null, "No more items", NewsFeedType.FACEBOOK))
    }

    fun refreshTimeline() {
        request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                { response ->
                    request = response.getRequestForPagedResults(GraphResponse.PagingDirection.NEXT)
                }
        )

        request?.executeAsync()
    }
}