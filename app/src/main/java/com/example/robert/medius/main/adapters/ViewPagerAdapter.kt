package com.example.robert.medius.main.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.robert.medius.R
import com.example.robert.medius.newsFeed.types.NewsFeedType
import com.example.robert.medius.newsFeed.ui.NewsFeedFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ViewPagerAdapter(fm: FragmentManager, private val fragments: MutableList<NewsFeedFragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val name = fragments[position].feedType.name
        return name
    }

    fun getPageColor(position: Int)
            = if (position in 0..fragments.size - 1) fragments[position].feedType.color else R.color.colorAccent

    fun set(items: MutableList<NewsFeedFragment>) {
        fragments.clear()
        fragments.addAll(items)
        notifyDataSetChanged()
    }

    fun remove(type: NewsFeedType) {
        val index = fragments.indexOfFirst { it.feedType == type }
        if (index != -1) {
            fragments.removeAt(index)
            notifyDataSetChanged()
        }
    }

}