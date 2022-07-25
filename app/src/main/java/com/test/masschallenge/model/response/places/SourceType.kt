package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class SourceType(@SerializedName("name")
                      val name: String = "",
                      @SerializedName("id")
                      val id: Int = 0)