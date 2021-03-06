package com.example.robert.medius.newsFeed.ui

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robert.medius.R
import com.example.robert.medius.entities.News
import com.example.robert.medius.libs.di.LibsModule
import com.example.robert.medius.newsFeed.NewsFeedInteractor
import com.example.robert.medius.newsFeed.NewsFeedPresenter
import com.example.robert.medius.newsFeed.adapters.NewsFeedAdapter
import com.example.robert.medius.newsFeed.di.DaggerNewsFeedComponent
import com.example.robert.medius.newsFeed.di.NewsFeedComponent
import com.example.robert.medius.newsFeed.di.NewsFeedModule
import com.example.robert.medius.newsFeed.types.NewsFeedType
import kotlinx.android.synthetic.main.fragment_newsfeed.*
import kotlinx.android.synthetic.main.progress_view.*
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by robert on 4.7.2017.
 */
class NewsFeedFragment() : Fragment(), NewsFeedView {


    companion object {
        private const val TAG: String = "NewsFeedFragment"
        private const val NEWS_LIST = "News_list"
        fun newInstance(type: NewsFeedType): NewsFeedFragment {
            val fragment = NewsFeedFragment()
            fragment.feedType = type
            return fragment
        }
    }

    val newsFeedComponent: NewsFeedComponent by lazy {
        DaggerNewsFeedComponent.builder()
                .libsModule(LibsModule(this))
                .newsFeedModule(NewsFeedModule(this, context))
                .build()
    }

    override var feedType: NewsFeedType = NewsFeedType.NONE
    @Inject lateinit var presenter: NewsFeedPresenter<NewsFeedView, NewsFeedInteractor>
    @Inject lateinit var adapter: NewsFeedAdapter
    @Inject lateinit var layoutManager: LinearLayoutManager
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsFeedComponent.inject(this)

        savedInstanceState?.let {
            feedType = it.getSerializable(NewsFeedType::class.java.simpleName) as NewsFeedType
            layoutManager.onRestoreInstanceState(it.getParcelable(RecyclerView.LayoutManager::class.java.simpleName))
            adapter.set(it.getParcelableArrayList(NEWS_LIST))
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater!!.inflate(R.layout.fragment_newsfeed, container, false)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        progressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(context, feedType.color),
                android.graphics.PorterDuff.Mode.MULTIPLY)

        swipeRefreshLayout.setOnRefreshListener { presenter.onRefresh() }
        swipeRefreshLayout.setColorSchemeResources(feedType.color)

        rvNewsFeed.adapter = adapter
        rvNewsFeed.layoutManager = layoutManager
        rvNewsFeed.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvNewsFeed.addOnScrollListener(object : OnLoadMoreScrollListener() {
            override fun onLoadMore() {
                presenter.onLoadMore(adapter.getLastItem())
            }
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(NewsFeedType::class.java.simpleName, feedType)
        outState.putParcelable(RecyclerView.LayoutManager::class.java.simpleName, layoutManager.onSaveInstanceState())
        outState.putParcelableArrayList(NEWS_LIST, ArrayList<News>(adapter.news))
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        swipeRefreshLayout.visibility = View.GONE
        progressView.visibility = View.VISIBLE
    }

    override fun showEmpty() {
        rvNewsFeed.visibility = View.GONE
        progressView.visibility = View.GONE
        swipeRefreshLayout.visibility = View.VISIBLE
        emptyView.visibility = View.VISIBLE
    }

    override fun showContent() {
        emptyView.visibility = View.GONE
        progressView.visibility = View.GONE
        swipeRefreshLayout.visibility = View.VISIBLE
        rvNewsFeed.visibility = View.VISIBLE
    }

    override fun setRefreshing(isRefreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = isRefreshing
    }

    override fun showError(error: String) {
        context.toast(error)
    }

    override fun postDelay(task: () -> Unit, delay: Long) {
        handler.postDelayed({ task() }, delay)
    }

    override fun addContent(items: List<News>) {
        adapter.addAll(items)
    }

    override fun setContent(items: List<News>) {
        adapter.set(items)
    }

    override fun setIsMoreItems(isMoreItems: Boolean) {
        adapter.isMoreItems = isMoreItems
    }

    override fun getItemCount(): Int = adapter.itemCount
}
