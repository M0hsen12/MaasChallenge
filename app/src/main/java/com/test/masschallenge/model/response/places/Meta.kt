package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class Meta(@SerializedName("next")
                val next: String = "",
                @SerializedName("count")
                val count: String = "")