package com.test.masschallenge.model.response.activities

import com.google.gson.annotations.SerializedName
import com.test.masschallenge.model.response.places.Location

data class Address(@SerializedName("location")
                   val location: Location,
                   @SerializedName("city")
                   val city: String = "",
                   @SerializedName("streetName")
                   val streetName: String = "",
                   @SerializedName("postalCode")
                   val postalCode: String = "")