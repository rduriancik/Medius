package com.example.robert.medius.entities

import android.os.Parcel
import android.os.Parcelable
import com.example.robert.medius.newsFeed.types.NewsFeedType

/**
 * Created by robert on 3.8.2017.
 */

//@Parcelize
data class News(val id: String, val createdAt: Long, val url: String, val user: User?,
                val newsMedia: NewsMedia, val newsFeedType: NewsFeedType) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readLong(),
            source.readString(),
            source.readParcelable<User>(User::class.java.classLoader),
            source.readParcelable<NewsMedia>(NewsMedia::class.java.classLoader),
            NewsFeedType.values()[source.readInt()]
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeLong(createdAt)
        writeString(url)
        writeParcelable(user, 0)
        writeParcelable(newsMedia, 0)
        writeInt(newsFeedType.ordinal)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<News> = object : Parcelable.Creator<News> {
            override fun createFromParcel(source: Parcel): News = News(source)
            override fun newArray(size: Int): Array<News?> = arrayOfNulls(size)
        }
    }
}

//@Parcelize
data class NewsMedia(val text: String, val linksIndices: List<Pair<Int, Int>>? = null) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            ArrayList<Pair<Int, Int>>().apply { source.readList(this, Pair::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(text)
        writeList(linksIndices)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NewsMedia> = object : Parcelable.Creator<NewsMedia> {
            override fun createFromParcel(source: Parcel): NewsMedia = NewsMedia(source)
            override fun newArray(size: Int): Array<NewsMedia?> = arrayOfNulls(size)
        }
    }
}

//@Parcelize
data class User(val id: Long, val name: String, val photoUrl: String, val userUrl: String?) : Parcelable {
    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(name)
        writeString(photoUrl)
        writeString(userUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}