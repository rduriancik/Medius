package com.example.robert.medius.newsFeed.di

import com.example.robert.medius.newsFeed.ui.NewsFeedFragment
import dagger.Component

/**
 * Created by robert on 31.7.2017.
 */
@Component(modules = arrayOf(NewsFeedModule::class))
interface NewsFeedComponent {
    fun inject(newsFeedFragment: NewsFeedFragment)
}