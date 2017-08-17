package com.example.robert.medius.newsFeed

import com.example.robert.medius.base.Interactor
import com.example.robert.medius.newsFeed.entities.News
import com.example.robert.medius.newsFeed.types.NewsFeedType

/**
 * Created by robert on 31.7.2017.
 */
interface NewsFeedInteractor : Interactor {
    fun refreshTimeline(feedType: NewsFeedType)
    fun loadMoreTimeline(lastItem: News?, feedType: NewsFeedType)
}