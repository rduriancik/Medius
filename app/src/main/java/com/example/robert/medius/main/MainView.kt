package com.example.robert.medius.main

import com.example.robert.medius.base.View
import com.example.robert.medius.newsFeed.NewsFeedFragment

/**
 * Created by robert on 31.7.2017.
 */
interface MainView : View {
    fun setContent(items: MutableList<NewsFeedFragment>)
}