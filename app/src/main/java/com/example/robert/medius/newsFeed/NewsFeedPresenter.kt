package com.example.robert.medius.newsFeed

import com.example.robert.medius.base.Interactor
import com.example.robert.medius.base.Presenter
import com.example.robert.medius.base.View
import com.example.robert.medius.entities.News
import com.example.robert.medius.newsFeed.events.NewsFeedEvent

/**
 * Created by robert on 31.7.2017.
 */
interface NewsFeedPresenter<T : View, I : Interactor> : Presenter<T, I> {
    fun onPause()
    fun onResume()
    fun onRefresh()
    fun onLoadMore(news: News?)
    fun onEventMainThread(newsFeedEvent: NewsFeedEvent)
}