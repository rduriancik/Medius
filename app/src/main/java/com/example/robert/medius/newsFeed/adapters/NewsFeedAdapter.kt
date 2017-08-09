package com.example.robert.medius.newsFeed.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
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
    : RecyclerView.Adapter<NewsFeedAdapter.NewsViewHolder>() {

    override fun onBindViewHolder(holder: NewsViewHolder?, position: Int) {
        holder?.bind(news[position], imageLoader)
    }

    override fun getItemCount(): Int {
        return news.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_newsfeed, parent, false)
        return NewsViewHolder(view)
    }

    fun set(news: List<News>) {
        this.news.clear()
        addAll(news)
    }

    fun addAll(news: List<News>) {
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val userPhoto: CircleImageView
        private val socialMediaLogo: ImageView
        private val date: TextView
        private val userName: TextView
        private val text: TextView
        private val webView: WebView

        init {
            userPhoto = view.findViewById(R.id.userPhoto) as CircleImageView
            socialMediaLogo = view.findViewById(R.id.socialMediaLogo) as ImageView
            date = view.findViewById(R.id.date) as TextView
            userName = view.findViewById(R.id.userName) as TextView
            text = view.findViewById(R.id.text) as TextView
            webView = view.findViewById(R.id.webView) as WebView
        }

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
}