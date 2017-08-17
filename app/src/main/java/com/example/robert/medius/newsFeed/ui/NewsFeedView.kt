package com.example.robert.medius.newsFeed.ui

import com.example.robert.medius.base.View
import com.example.robert.medius.entities.News
import com.example.robert.medius.newsFeed.types.NewsFeedType

/**
 * Created by robert on 31.7.2017.
 */
interface NewsFeedView : View {
    var feedType: NewsFeedType

    fun showProgress()
    fun showEmpty()
    fun showContent()
    fun showError(error: String)
    fun setRefreshing(isRefreshing: Boolean)
    fun addContent(items: List<News>)
    fun setContent(items: List<News>)
    fun setIsMoreItems(isMoreItems: Boolean)
    fun getItemCount(): Int
    fun postDelay(task: () -> Unit, delay: Long)
}