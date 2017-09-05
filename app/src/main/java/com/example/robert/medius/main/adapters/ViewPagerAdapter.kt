package com.example.robert.medius.main.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.robert.medius.R
import com.example.robert.medius.newsFeed.ui.NewsFeedFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ViewPagerAdapter(fm: FragmentManager, private val fragments: MutableList<NewsFeedFragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments.get(position)

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = fragments[position].feedType.name

    fun getPageColor(position: Int)
            = if (position in 0 until fragments.size) fragments[position].feedType.color else R.color.colorAccent

    fun set(items: MutableList<NewsFeedFragment>) {
        fragments.clear()
        fragments.addAll(items)
        notifyDataSetChanged()
    }
}