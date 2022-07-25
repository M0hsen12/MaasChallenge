package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class TagsItem(@SerializedName("name")
                    val name: String = "",
                    @SerializedName("id")
                    val id: String = "")