package com.example.robert.medius.newsFeed.di

import com.example.robert.medius.entities.News
import com.example.robert.medius.libs.base.EventBus
import com.example.robert.medius.libs.base.ImageLoader
import com.example.robert.medius.newsFeed.NewsFeedInteractor
import com.example.robert.medius.newsFeed.NewsFeedInteractorImpl
import com.example.robert.medius.newsFeed.NewsFeedPresenter
import com.example.robert.medius.newsFeed.NewsFeedPresenterImpl
import com.example.robert.medius.newsFeed.adapters.NewsFeedAdapter
import com.example.robert.medius.newsFeed.ui.NewsFeedView
import com.example.robert.medius.twitter.TwitterApiHelper
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.services.StatusesService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by robert on 31.7.2017.
 */
@Module
class NewsFeedModule(private val view: NewsFeedView) {

    @Provides
    fun provideNewsFeedPresenter(interactor: NewsFeedInteractor, eventBus: EventBus): NewsFeedPresenter<NewsFeedView, NewsFeedInteractor>
            = NewsFeedPresenterImpl(view, interactor, eventBus)

    @Provides
    fun provideNewsFeedInteractor(twitterApiHelper: TwitterApiHelper): NewsFeedInteractor
            = NewsFeedInteractorImpl(twitterApiHelper)

    @Provides
    @Singleton
    fun provideTwitterApiHelper(statusesService: StatusesService, eventBus: EventBus): TwitterApiHelper
            = TwitterApiHelper(statusesService, eventBus)

    @Provides
    @Singleton
    fun provideStatusService(): StatusesService = TwitterCore.getInstance().apiClient.statusesService

    @Provides
    fun provideNewsFeedAdapter(items: MutableList<News>, imageLoader: ImageLoader): NewsFeedAdapter
            = NewsFeedAdapter(items, imageLoader)

    @Provides
    fun provideMutableList(): MutableList<News> = mutableListOf<News>()

}