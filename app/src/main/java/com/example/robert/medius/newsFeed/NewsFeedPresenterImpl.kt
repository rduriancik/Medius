package com.example.robert.medius.newsFeed

import com.example.robert.medius.entities.News
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

    override fun onRefresh() {
        view?.setRefreshing(true)
        interactor.refreshTimeline(view?.feedType ?: NewsFeedType.NONE)
    }

    override fun onLoadMore(news: News?) {
        if (view?.getItemCount() == 1) {
            view?.showProgress()
        }

        view?.postDelay({
            interactor.loadMoreTimeline(news, view?.feedType ?: NewsFeedType.NONE)
        }, 1000)
    }

    @Subscribe
    override fun onEventMainThread(newsFeedEvent: NewsFeedEvent) {
        if (newsFeedEvent.newsFeedType == view?.feedType) {
            when (newsFeedEvent) {
                is NewsFeedEvent.RefreshEvent -> {
                    newsFeedEvent.news?.let {
                        view?.setContent(it)
                    }
                    view?.setRefreshing(false)
                    newsFeedEvent.error?.let { view?.showError(it) }
                }

                is NewsFeedEvent.LoadMoreEvent -> {
                    if (newsFeedEvent.news != null) {
                        view?.showContent()
                        view?.addContent(newsFeedEvent.news!!)
                    } else {
                        view?.setIsMoreItems(false)
                    }

                    if (view?.getItemCount() == 1) {
                        view?.showEmpty()
                    }

                    newsFeedEvent.error?.let { view?.showError(it) }
                }
            }

        }
    }
}