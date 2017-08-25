package com.example.robert.medius.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by robert on 19.8.2017.
 */

data class FacebookNews(val id: String, val message: String?, val story: String?, @SerializedName("created_time") val createdAt: String)