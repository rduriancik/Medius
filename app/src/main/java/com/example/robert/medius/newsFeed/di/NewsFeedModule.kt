package com.example.robert.medius.newsFeed.di

import com.example.robert.medius.newsFeed.NewsFeedInteractor
import com.example.robert.medius.newsFeed.NewsFeedInteractorImpl
import com.example.robert.medius.newsFeed.NewsFeedPresenter
import com.example.robert.medius.newsFeed.NewsFeedPresenterImpl
import com.example.robert.medius.newsFeed.adapters.NewsFeedAdapter
import com.example.robert.medius.newsFeed.entities.News
import com.example.robert.medius.newsFeed.ui.NewsFeedView
import dagger.Module
import dagger.Provides

/**
 * Created by robert on 31.7.2017.
 */
@Module
class NewsFeedModule(private val view: NewsFeedView) {

    @Provides
    fun provideNewsFeedPresenter(interactor: NewsFeedInteractor): NewsFeedPresenter<NewsFeedView, NewsFeedInteractor>
            = NewsFeedPresenterImpl(view, interactor)

    @Provides
    fun provideNewsFeedInteractor(): NewsFeedInteractor = NewsFeedInteractorImpl()

    @Provides
    fun provideNewsFeedAdapter(items: MutableList<News>): NewsFeedAdapter = NewsFeedAdapter(items)

    @Provides
    fun provideMutableList(): MutableList<News> = mutableListOf<News>()
}