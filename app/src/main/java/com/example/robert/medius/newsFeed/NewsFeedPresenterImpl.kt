package com.example.robert.medius.newsFeed

import com.example.robert.medius.libs.base.EventBus
import com.example.robert.medius.newsFeed.events.NewsFeedEvent
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.example.robert.medius.newsFeed.ui.NewsFeedView
import org.greenrobot.eventbus.Subscribe

/**
 * Created by robert on 3.8.2017.
 */
class NewsFeedPresenterImpl(override var view: NewsFeedView?
                            , override val interactor: NewsFeedInteractor
                            , val eventBus: EventBus)
    : NewsFeedPresenter<NewsFeedView, NewsFeedInteractor> {

    override fun onResume() {
        eventBus.register(this)
    }

    override fun onPause() {
        eventBus.unregister(this)
    }

    override fun getInitItems() {
        view?.showProgress()
        interactor.initTimeline(view?.feedType ?: NewsFeedType.NONE)
    }

    override fun onRefresh() {
        view?.setRefreshing(true)
        interactor.refreshTimeline(view?.feedType ?: NewsFeedType.NONE)
    }

    override fun onLoadMore(id: Long?) {
        // TODO implement
    }

    @Subscribe
    override fun onEventMainThread(newsFeedEvent: NewsFeedEvent) {
        if (newsFeedEvent.newsFeedType == view?.feedType) {
            when (newsFeedEvent) {
                is NewsFeedEvent.InitTimelineEvent -> {
                    if (newsFeedEvent.news != null) {
                        view?.showContent()
                        view?.setContent(newsFeedEvent.news!!)
                    } else {
                        view?.showEmpty()
                    }

                    newsFeedEvent.error?.let { view?.showError(it) }
                }

                is NewsFeedEvent.RefreshEvent -> {
                    newsFeedEvent.news?.let {
                        view?.setContent(it)
                        view?.setRefreshing(false)
                    }

                    newsFeedEvent.error?.let { view?.showError(it) }
                }

                is NewsFeedEvent.LoadMoreEvent -> {
                    // TODO implement
                }
            }

        }
    }
}