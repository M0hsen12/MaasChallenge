package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class Address(@SerializedName("street_address")
                   val streetAddress: String = "",
                   @SerializedName("neighbourhood")
                   val neighbourhood: String = "",
                   @SerializedName("locality")
                   val locality: String = "",
                   @SerializedName("postal_code")
                   val postalCode: String = "")