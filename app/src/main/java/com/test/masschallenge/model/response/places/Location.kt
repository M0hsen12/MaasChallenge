package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class Location(@SerializedName("address")
                    val address: Address,
                    @SerializedName("lon", alternate = ["long"])
                    val lon: Double = 0.0,
                    @SerializedName("lat")
                    val lat: Double = 0.0)