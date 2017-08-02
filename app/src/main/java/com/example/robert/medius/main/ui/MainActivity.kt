package com.example.robert.medius.main.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.robert.medius.R
import com.example.robert.medius.loginSettings.ui.LoginSettingsActivity
import com.example.robert.medius.main.MainInteractor
import com.example.robert.medius.main.MainPresenter
import com.example.robert.medius.main.adapters.ViewPagerAdapter
import com.example.robert.medius.main.di.DaggerMainComponent
import com.example.robert.medius.main.di.MainModule
import com.example.robert.medius.newsFeed.ui.NewsFeedFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject lateinit var presenter: MainPresenter<MainView, MainInteractor>
    @Inject lateinit var viewPageAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupInjection()

        setSupportActionBar(toolbar)
        setupTabLayout()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setupTabLayout() {
        container.adapter = viewPageAdapter
        tabs.setupWithViewPager(container)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_social_media) {
            startActivity<LoginSettingsActivity>()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setContent(items: MutableList<NewsFeedFragment>) {
        viewPageAdapter.set(items)
    }

    private fun setupInjection() {
        DaggerMainComponent.builder()
                .mainModule(MainModule(supportFragmentManager, this))
                .build()
                .inject(this)
    }

}
