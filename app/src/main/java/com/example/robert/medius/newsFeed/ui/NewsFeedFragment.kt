package com.example.robert.medius.newsFeed.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robert.medius.R
import com.example.robert.medius.newsFeed.types.NewsFeedType
import kotlinx.android.synthetic.main.fragment_newsfeed.*

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

    var feedType: NewsFeedType = NewsFeedType.NONE

//    @Inject lateinit var presenter: NewsFeedPresenter<NewsFeedView>
//    @Inject lateinit var adapter: NewsFeedAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_newsfeed, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        rvNewsFeed.layoutManager = LinearLayoutManager(context)
//        rvNewsFeed.adapter = adapter
    }
}
