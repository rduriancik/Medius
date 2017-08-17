package com.example.robert.medius.newsFeed.di

import com.example.robert.medius.libs.di.LibsModule
import com.example.robert.medius.newsFeed.ui.NewsFeedFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by robert on 31.7.2017.
 */
@Singleton
@Component(modules = arrayOf(NewsFeedModule::class, LibsModule::class))
interface NewsFeedComponent {
    fun inject(newsFeedFragment: NewsFeedFragment)
}