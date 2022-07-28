package com.test.masschallenge.model.response.activities

import com.google.gson.annotations.SerializedName

data class PriceEUR(@SerializedName("from")
                    val from: Double = 0.0,
                    @SerializedName("to")
                    val to: Double = 0.0,
                    @SerializedName("pricingType")
                    val pricingType: String = "")