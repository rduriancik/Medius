package com.example.robert.medius.newsFeed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robert.medius.R
import kotlinx.android.synthetic.main.fragment_newsfeed.*

/**
 * Created by robert on 4.7.2017.
 */
class NewsFeedFragment() : Fragment() {

    companion object {
        const val NEWS_FEED_TYPE_KEY = "TYPE_KEY"

        fun newInstance(type: NewsFeedType): NewsFeedFragment {
            val fragment = NewsFeedFragment()
            fragment.feedType = type
            return fragment
        }
    }

    var feedType: NewsFeedType = NewsFeedType.NONE

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_newsfeed, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        rvNewsFeed.layoutManager = LinearLayoutManager(context)
        rvNewsFeed.adapter = NewsFeedAdapter()
    }
}
