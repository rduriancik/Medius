package com.example.robert.medius.main.di

import android.support.v4.app.FragmentManager
import com.example.robert.medius.main.MainInteractor
import com.example.robert.medius.main.MainInteractorImpl
import com.example.robert.medius.main.MainPresenter
import com.example.robert.medius.main.MainPresenterImpl
import com.example.robert.medius.main.adapters.ViewPagerAdapter
import com.example.robert.medius.main.ui.MainView
import com.example.robert.medius.newsFeed.ui.NewsFeedFragment
import dagger.Module
import dagger.Provides

/**
 * Created by robert on 31.7.2017.
 */
@Module
class MainModule(val fragmentManager: FragmentManager, val view: MainView) {

    @Provides
    fun provideViewPagerAdapter(fragmentManager: FragmentManager, fragments: MutableList<NewsFeedFragment>)
            = ViewPagerAdapter(fragmentManager, fragments)

    @Provides
    fun provideFragmentManager(): FragmentManager = fragmentManager

    @Provides
    fun provideFragments(): MutableList<NewsFeedFragment> = mutableListOf<NewsFeedFragment>()

    @Provides
    fun provideMainPresenter(mainInteractor: MainInteractor): MainPresenter<MainView, MainInteractor>
            = MainPresenterImpl(view, mainInteractor)

    @Provides
    fun provideMainInteractor(): MainInteractor = MainInteractorImpl()

    @Provides
    fun provideMainView(): MainView = view
}