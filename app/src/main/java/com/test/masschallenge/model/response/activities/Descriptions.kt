package com.test.masschallenge.model.response.activities

import com.google.gson.annotations.SerializedName

data class Descriptions(@SerializedName("fi")
                        val fi: Fi,
                        @SerializedName("en")
                        val en: En)