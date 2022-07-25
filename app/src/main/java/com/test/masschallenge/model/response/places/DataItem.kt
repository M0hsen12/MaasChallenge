package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("name")
                    val name: Name,
                    @SerializedName("opening_hours_url")
                    val openingHoursUrl: String = "",
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