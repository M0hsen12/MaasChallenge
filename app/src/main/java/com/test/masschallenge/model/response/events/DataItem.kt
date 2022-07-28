package com.test.masschallenge.model.response.events

import com.google.gson.annotations.SerializedName
import com.test.masschallenge.model.response.places.*

data class DataItem(@SerializedName("event_dates")
                    val eventDates: EventDates,
                    @SerializedName("name")
                    val name: Name,
                    @SerializedName("description")
                    val description: Description,
                    @SerializedName("source_type")
                    val sourceType: SourceType,
                    @SerializedName("location")
                    val location: Location,
                    @SerializedName("id")
                    val id: String = "",
                    @SerializedName("modified_at")
                    val modifiedAt: String = "",
                    @SerializedName("info_url")
                    val infoUrl: String = "",
                    @SerializedName("tags")
                    val tags: List<TagsItem>?)