package com.example.robert.medius.newsFeed.adapters

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.robert.medius.R
import com.example.robert.medius.libs.base.ImageLoader
import com.example.robert.medius.newsFeed.entities.News
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.browse

/**
 * Created by robert on 4.7.2017.
 */

class NewsFeedAdapter(private val news: MutableList<News>, private val imageLoader: ImageLoader)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = "ADAPTER"

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOADING = 0
    }

    var isMoreItems = true

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is NewsViewHolder -> {
                holder.bind(news[position], imageLoader)
            }
            is ProgressViewHolder -> {
//                holder?.setProgressBarColor(news[position]?.) FIXME
            }
        }
    }

    override fun getItemCount(): Int = news.size + 1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder

        if (viewType == VIEW_TYPE_ITEM || !isMoreItems) {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_newsfeed, parent, false)
            viewHolder = NewsViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.progress_view, parent, false)
            viewHolder = ProgressViewHolder(view)
        }

        return viewHolder
    }

    override fun getItemViewType(position: Int): Int
            = if (position < news.size) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING

    fun set(news: List<News>) {
        this.news.clear()
        addAll(news)
    }

    fun addAll(news: List<News>) {
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    fun getLastItem() = news.lastOrNull()

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val userPhoto: CircleImageView = view.findViewById(R.id.userPhoto) as CircleImageView
        private val socialMediaLogo: ImageView = view.findViewById(R.id.socialMediaLogo) as ImageView
        private val date: TextView = view.findViewById(R.id.date) as TextView
        private val userName: TextView = view.findViewById(R.id.userName) as TextView
        private val text: TextView = view.findViewById(R.id.text) as TextView
        private val webView: WebView = view.findViewById(R.id.webView) as WebView

        fun bind(news: News, imageLoader: ImageLoader) {
            text.text = news.newsMedia.text
            userName.text = news.user.name
            userName.setOnClickListener { view.context.browse(news.user.userUrl ?: "") } //FIXME
            date.text = news.createdAt
            imageLoader.load(news.user.photoUrl, userPhoto)
            imageLoader.load(news.socialMediaLogo, socialMediaLogo)
//
//            webView.settings.javaScriptEnabled = true
//            webView.setWebChromeClient(WebChromeClient())
//            webView.loadUrl("https://pbs.twimg.com/media/DGxnH7FXUAAekh-.jpg")
        }

    }

    class ProgressViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val progressBar: ProgressBar = view.findViewById(R.id.progressBar) as ProgressBar

        fun setProgressBarColor(@ColorRes color: Int) {
            progressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(view.context, color),
                    android.graphics.PorterDuff.Mode.MULTIPLY)
        }
    }
}