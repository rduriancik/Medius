package com.example.robert.medius.newsFeed.events

import com.example.robert.medius.newsFeed.entities.News
import com.example.robert.medius.newsFeed.types.NewsFeedType

/**
 * Created by robert on 7.8.2017.
 */

sealed class NewsFeedEvent(var news: List<News>?, var error: String?, val newsFeedType: NewsFeedType) {

    class InitTimelineEvent(news: List<News>?, error: String?, feedType: NewsFeedType)
        : NewsFeedEvent(news, error, feedType)

    class RefreshEvent(news: List<News>?, error: String?, feedType: NewsFeedType)
        : NewsFeedEvent(news, error, feedType)

    class LoadMoreEvent(news: List<News>?, error: String?, feedType: NewsFeedType)
        : NewsFeedEvent(news, error, feedType)
}
