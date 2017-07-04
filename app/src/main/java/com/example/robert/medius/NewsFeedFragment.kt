package com.example.robert.medius

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by robert on 4.7.2017.
 */
class NewsFeedFragment() : Fragment() {

    companion object {
        fun newInstance(): NewsFeedFragment {
            val fragment = NewsFeedFragment()
            val args = Bundle()
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater!!.inflate(R.layout.fragment_newsfeed, container, false)

        return rootView
    }
}