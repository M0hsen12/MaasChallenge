package com.test.masschallenge.model.response.activities

import com.google.gson.annotations.SerializedName

data class Fi(@SerializedName("name")
              val name: String = "",
              @SerializedName("description")
              val description: String = "")