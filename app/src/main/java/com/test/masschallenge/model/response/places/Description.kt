package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("intro")
    val intro: String = "",
    @SerializedName("body")
    val body: String = "",
    @SerializedName("images")
    val images: List<Images>?
)