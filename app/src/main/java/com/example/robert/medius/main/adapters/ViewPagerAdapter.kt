package com.example.robert.medius.main.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.robert.medius.newsFeed.NewsFeedFragment
import com.example.robert.medius.newsFeed.NewsFeedType

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ViewPagerAdapter(fm: FragmentManager, val fragments: MutableList<NewsFeedFragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? = fragments[position].getFeedType().name

    fun add(type: NewsFeedType) {
        fragments.add(NewsFeedFragment.newInstance(type))
        notifyDataSetChanged()
    }

    fun remove(type: NewsFeedType) {
        val index = fragments.indexOfFirst { it.getFeedType() == type }
        if (index != -1) {
            fragments.removeAt(index)
            notifyDataSetChanged()
        }
    }

}