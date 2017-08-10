package com.example.robert.medius.newsFeed.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robert.medius.R
import com.example.robert.medius.libs.di.LibsModule
import com.example.robert.medius.newsFeed.NewsFeedInteractor
import com.example.robert.medius.newsFeed.NewsFeedPresenter
import com.example.robert.medius.newsFeed.OnLoadMoreListener
import com.example.robert.medius.newsFeed.adapters.NewsFeedAdapter
import com.example.robert.medius.newsFeed.di.DaggerNewsFeedComponent
import com.example.robert.medius.newsFeed.di.NewsFeedComponent
import com.example.robert.medius.newsFeed.di.NewsFeedModule
import com.example.robert.medius.newsFeed.entities.News
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
        fun newInstance(type: NewsFeedType): NewsFeedFragment {
            val fragment = NewsFeedFragment()
            fragment.feedType = type
            return fragment
        }
    }

    val newsFeedComponent: NewsFeedComponent by lazy {
        DaggerNewsFeedComponent.builder()
                .libsModule(LibsModule(this))
                .newsFeedModule(NewsFeedModule(this))
                .build()
    }

    override var feedType: NewsFeedType = NewsFeedType.NONE
    @Inject lateinit var presenter: NewsFeedPresenter<NewsFeedView, NewsFeedInteractor>
    @Inject lateinit var adapter: NewsFeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            feedType = it.getSerializable(NewsFeedType::class.java.simpleName) as NewsFeedType
        }
        newsFeedComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater!!.inflate(R.layout.fragment_newsfeed, container, false)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        progressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(context, feedType.color),
                android.graphics.PorterDuff.Mode.MULTIPLY)

        swipeRefreshLayout.setOnRefreshListener { presenter.onRefresh() }
        swipeRefreshLayout.setColorSchemeResources(feedType.color)

        val linearLayoutManager = LinearLayoutManager(context)
        rvNewsFeed.layoutManager = linearLayoutManager
        adapter.onLoadMoreListener = object : OnLoadMoreListener {
            override fun onLoadMore(id: Long?) {
                presenter.onLoadMore(id) // TODO check if its called at the beginning and adjust events and initial filling afterwards
            }
        }
        rvNewsFeed.adapter = adapter
        rvNewsFeed.addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))

        if (adapter.itemCount == 0) {
            presenter.getInitItems()
        }
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
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        swipeRefreshLayout.visibility = View.GONE
        emptyView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun showEmpty() {
        swipeRefreshLayout.visibility = View.GONE
        progressBar.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
    }

    override fun showContent() {
        emptyView.visibility = View.GONE
        progressBar.visibility = View.GONE
        swipeRefreshLayout.visibility = View.VISIBLE
    }

    override fun setRefreshing(isRefreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = isRefreshing
    }

    override fun showError(error: String) {
        context.toast(error)
    }

    override fun addContent(items: List<News>) {
        adapter.addAll(items)
    }

    override fun setContent(items: List<News>) {
        adapter.set(items)
    }
}
