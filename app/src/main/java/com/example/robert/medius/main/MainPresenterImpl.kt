package com.example.robert.medius.main

import com.example.robert.medius.main.ui.MainView
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.example.robert.medius.newsFeed.ui.NewsFeedFragment

/**
 * Created by robert on 31.7.2017.
 */
class MainPresenterImpl(override var view: MainView?, override val interactor: MainInteractor)
    : MainPresenter<MainView, MainInteractor> {

    override fun onResume() {
        val fragments: MutableList<NewsFeedFragment> = mutableListOf()

        if (interactor.isFacebookLoggedIn()) {
            fragments.add(NewsFeedFragment.newInstance(NewsFeedType.FACEBOOK))
        }

        if (interactor.isTwitterLoggedIn()) {
            fragments.add(NewsFeedFragment.newInstance(NewsFeedType.TWITTER))
        }

        view?.setContent(fragments)
    }
}