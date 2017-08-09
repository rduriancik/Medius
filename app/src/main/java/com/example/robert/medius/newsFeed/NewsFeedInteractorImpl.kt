package com.example.robert.medius.newsFeed

import com.example.robert.medius.newsFeed.entities.News
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.example.robert.medius.twitter.TwitterApiHelper

/**
 * Created by robert on 3.8.2017.
 */
class NewsFeedInteractorImpl(val twitterApiHelper: TwitterApiHelper) : NewsFeedInteractor {
    override fun initTimeline(feedType: NewsFeedType) {
        when (feedType) {
            NewsFeedType.FACEBOOK -> return // TODO
            NewsFeedType.TWITTER -> twitterApiHelper.initTimeline()
            NewsFeedType.NONE -> return // TODO
        }
    }

    override fun refreshTimeline(feedType: NewsFeedType) {
        when (feedType) {
            NewsFeedType.FACEBOOK -> return // TODO
            NewsFeedType.TWITTER -> twitterApiHelper.refreshTimeline()
            NewsFeedType.NONE -> return // TODO
        }
    }

    override fun loadMoreTimeline(lastItem: News, feedType: NewsFeedType) {
        when (feedType) {
            NewsFeedType.FACEBOOK -> return // TODO
            NewsFeedType.TWITTER -> twitterApiHelper.loadMoreTimeline()
            NewsFeedType.NONE -> return // TODO
        }
    }
}