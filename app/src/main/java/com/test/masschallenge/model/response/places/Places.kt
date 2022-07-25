package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class Places(@SerializedName("data")
                  val data: List<DataItem>?,
                  @SerializedName("meta")
                  val meta: Meta,
                  @SerializedName("tags")
                  val tags: Tags)