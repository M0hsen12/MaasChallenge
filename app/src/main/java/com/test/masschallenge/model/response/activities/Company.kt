package com.test.masschallenge.model.response.activities

import com.google.gson.annotations.SerializedName

data class Company(@SerializedName("phone")
                   val phone: String = "",
                   @SerializedName("name")
                   val name: String = "",
                   @SerializedName("businessId")
                   val businessId: String = "",
                   @SerializedName("email")
                   val email: String = "")