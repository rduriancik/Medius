package com.example.robert.medius.newsFeed

import com.example.robert.medius.newsFeed.ui.NewsFeedView

/**
 * Created by robert on 3.8.2017.
 */
class NewsFeedPresenterImpl(override var view: NewsFeedView?, override val interactor: NewsFeedInteractor)
    : NewsFeedPresenter<NewsFeedView, NewsFeedInteractor> {

}