package com.example.robert.medius.newsFeed.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robert.medius.R
import com.example.robert.medius.newsFeed.NewsFeedInteractor
import com.example.robert.medius.newsFeed.NewsFeedPresenter
import com.example.robert.medius.newsFeed.adapters.NewsFeedAdapter
import com.example.robert.medius.newsFeed.di.DaggerNewsFeedComponent
import com.example.robert.medius.newsFeed.di.NewsFeedModule
import com.example.robert.medius.newsFeed.entities.News
import com.example.robert.medius.newsFeed.types.NewsFeedType
import kotlinx.android.synthetic.main.fragment_newsfeed.*
import javax.inject.Inject

/**
 * Created by robert on 4.7.2017.
 */
class NewsFeedFragment() : Fragment(), NewsFeedView {

    companion object {
        fun newInstance(type: NewsFeedType): NewsFeedFragment {
            val fragment = NewsFeedFragment()
            fragment.feedType = type
            return fragment
        }
    }

    override var feedType: NewsFeedType = NewsFeedType.NONE
    @Inject lateinit var presenter: NewsFeedPresenter<NewsFeedView, NewsFeedInteractor>
    @Inject lateinit var adapter: NewsFeedAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_newsfeed, container, false)
        setupInjection()
        return view
    }

    private fun setupInjection() {
        DaggerNewsFeedComponent.builder()
                .newsFeedModule(NewsFeedModule(this))
                .build()
                .inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        rvNewsFeed.layoutManager = LinearLayoutManager(context)
        rvNewsFeed.adapter = adapter
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

    override fun addContent(items: List<News>) {
        adapter.addAll(items)
    }
}
