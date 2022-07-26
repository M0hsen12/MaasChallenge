package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class LicenseType(@SerializedName("name")
                       val name: String = "",
                       @SerializedName("id")
                       val id: Int = 0)