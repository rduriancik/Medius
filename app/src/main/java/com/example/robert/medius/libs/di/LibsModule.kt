package com.example.robert.medius.libs.di

import android.support.v4.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.robert.medius.libs.GlideImageLoader
import com.example.robert.medius.libs.GreenRobotEventBus
import com.example.robert.medius.libs.base.EventBus
import com.example.robert.medius.libs.base.ImageLoader
import dagger.Module
import dagger.Provides

/**
 * Created by robert on 7.8.2017.
 */
@Module
class LibsModule(val fragment: Fragment) {

    @Provides
    fun provideEventBus(eventBus: org.greenrobot.eventbus.EventBus): EventBus
            = GreenRobotEventBus(eventBus)

    @Provides
    fun provideGreenRobotEventBus(): org.greenrobot.eventbus.EventBus
            = org.greenrobot.eventbus.EventBus.getDefault()

    @Provides
    fun provideImageLoader(requestManager: RequestManager): ImageLoader
            = GlideImageLoader(requestManager)

    @Provides
    fun provideRequestManager(fragment: Fragment): RequestManager = Glide.with(fragment)

    @Provides
    fun provideFragment(): Fragment = this.fragment
}