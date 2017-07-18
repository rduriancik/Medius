package com.example.robert.medius

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mViewPageAdapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        setupTabLayout()
    }

    private fun setupTabLayout() {
        mViewPageAdapter = ViewPagerAdapter(supportFragmentManager)

        container.adapter = mViewPageAdapter

        tabs.setupWithViewPager(container)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_social_media) {
            startActivity(Intent(this, SocialMediaActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
