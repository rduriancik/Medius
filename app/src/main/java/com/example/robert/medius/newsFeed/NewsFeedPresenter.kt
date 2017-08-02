package com.example.robert.medius.newsFeed

import com.example.robert.medius.base.Interactor
import com.example.robert.medius.base.Presenter
import com.example.robert.medius.base.View

/**
 * Created by robert on 31.7.2017.
 */
interface NewsFeedPresenter<T : View, I : Interactor> : Presenter<T, I> {
}