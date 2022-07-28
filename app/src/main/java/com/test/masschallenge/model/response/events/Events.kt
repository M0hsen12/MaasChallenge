package com.test.masschallenge.model.response.events

import com.google.gson.annotations.SerializedName
import com.test.masschallenge.model.response.places.Meta

data class Events(@SerializedName("data")
                  val data: List<DataItem>?,
                  @SerializedName("meta")
                  val meta: Meta,
                  @SerializedName("tags")
                  val tags: Tags)