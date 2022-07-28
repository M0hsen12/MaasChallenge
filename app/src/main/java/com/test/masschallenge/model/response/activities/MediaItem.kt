package com.test.masschallenge.model.response.activities

import com.google.gson.annotations.SerializedName

data class MediaItem(@SerializedName("smallUrl")
                     val smallUrl: String = "",
                     @SerializedName("copyright")
                     val copyright: String = "",
                     @SerializedName("kind")
                     val kind: String = "",
                     @SerializedName("name")
                     val name: String = "",
                     @SerializedName("alt")
                     val alt: String = "",
                     @SerializedName("largeUrl")
                     val largeUrl: String = "",
                     @SerializedName("id")
                     val id: String = "",
                     @SerializedName("originalUrl")
                     val originalUrl: String = "")