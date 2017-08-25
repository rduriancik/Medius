package com.example.robert.medius.newsFeed

import com.example.robert.medius.entities.News
import com.example.robert.medius.facebook.FacebookApiHelper
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.example.robert.medius.twitter.TwitterApiHelper

/**
 * Created by robert on 3.8.2017.
 */
class NewsFeedInteractorImpl(val twitterApiHelper: TwitterApiHelper, val facebookApiHelper: FacebookApiHelper) : NewsFeedInteractor {

    override fun refreshTimeline(feedType: NewsFeedType) {
        when (feedType) {
            NewsFeedType.FACEBOOK -> facebookApiHelper.refreshTimeline()
            NewsFeedType.TWITTER -> twitterApiHelper.refreshTimeline()
            NewsFeedType.NONE -> throw IllegalStateException("Calling this method when a NewsFeedType of the fragment is equals to NONE is not allowed")
        }
    }

    override fun loadMoreTimeline(lastItem: News?, feedType: NewsFeedType) {
        when (feedType) {
            NewsFeedType.FACEBOOK -> facebookApiHelper.loadMoreTimeline()
            NewsFeedType.TWITTER -> twitterApiHelper.loadMoreTimeline(if (lastItem != null) lastItem.id.toLong() - 1 else null)
            NewsFeedType.NONE -> throw IllegalStateException("Calling this method when a NewsFeedType of the fragment is equals to NONE is not allowed")
        }
    }
}