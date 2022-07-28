package com.test.masschallenge.model.response.activities

import com.google.gson.annotations.SerializedName

data class Activities(@SerializedName("offset")
                      val offset: Int = 0,
                      @SerializedName("count")
                      val count: Int = 0,
                      @SerializedName("rows")
                      val rows: List<RowsItem>?)