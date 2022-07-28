package com.test.masschallenge.model.response.events

import com.google.gson.annotations.SerializedName

data class Tags(@SerializedName("linkedevents:yso:p5231")
                val link1: String = "",
                @SerializedName("linkedevents:yso:p19245")
                val link2: String = "",
                @SerializedName("linkedevents:yso:p9270")
                val link3: String = "",
                @SerializedName("linkedevents:yso:p6213")
                val link4: String = "")