package com.example.robert.medius

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    private var mViewPageAdapter: ViewPagerAdapter? = null
    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupTabLayout()
    }

    private fun setupTabLayout() {
        mViewPageAdapter = ViewPagerAdapter(supportFragmentManager)

        mViewPager = find<ViewPager>(R.id.container)
        mViewPager!!.adapter = mViewPageAdapter

        val tabLayout = find<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(mViewPager)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//
//
//        if (id == R.id.action_settings) {
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
//    }

}
