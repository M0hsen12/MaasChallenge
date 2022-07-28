package com.test.masschallenge.model.response.events

import com.google.gson.annotations.SerializedName

data class EventDates(@SerializedName("starting_day")
                      val startingDay: String = "",
                      @SerializedName("ending_day")
                      val endingDay: String = "")