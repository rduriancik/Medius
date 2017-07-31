package com.example.robert.medius.main

import com.example.robert.medius.newsFeed.NewsFeedFragment
import com.example.robert.medius.newsFeed.NewsFeedType

/**
 * Created by robert on 31.7.2017.
 */
class MainPresenterImpl(override var view: MainView?, val interactor: MainInteractor)
    : MainPresenter<MainView> {

    override fun onCreate() {
        val fragments: MutableList<NewsFeedFragment> = mutableListOf()

        if (interactor.isTwitterLoggedIn()) {
            fragments.add(NewsFeedFragment.newInstance(NewsFeedType.TWITTER))
        }

        view?.setContent(fragments)
    }
}