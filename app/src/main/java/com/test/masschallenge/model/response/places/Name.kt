package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class Name(@SerializedName("fi")
                val fi: String = "",
                @SerializedName("sv")
                val sv: String = "",
                @SerializedName("en")
                val en: String = "")