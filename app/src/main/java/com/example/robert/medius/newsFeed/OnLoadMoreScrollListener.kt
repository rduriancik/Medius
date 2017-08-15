package com.example.robert.medius.newsFeed

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by robert on 15.8.2017.
 */
abstract class OnLoadMoreScrollListener : RecyclerView.OnScrollListener() {
//    private val TAG = "OnLoadMoreScrollLis"

    private var mPreviousTotal = 0
    private var mLoading = true

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = recyclerView?.adapter?.itemCount ?: 0
        val visibleItemCount = recyclerView?.childCount ?: 0
        val firstVisibleItem = (recyclerView?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

//        Log.d(TAG, "totalItemCount ${totalItemCount}")
//        Log.d(TAG, "visibleItemCount ${visibleItemCount}")
//        Log.d(TAG, "firstVisibleItem ${firstVisibleItem}")
//        Log.d(TAG, "mPreviousTotal ${mPreviousTotal}")
//        Log.d(TAG, "mLoading ${mLoading}")
//        Log.d(TAG, "")

        if (totalItemCount < mPreviousTotal) {
            mPreviousTotal = 0
        }

        if (mLoading && totalItemCount > mPreviousTotal) {
            mLoading = false
            mPreviousTotal = totalItemCount
        }

        if (!mLoading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            onLoadMore()
            mLoading = true
        }

    }

    abstract fun onLoadMore()
}