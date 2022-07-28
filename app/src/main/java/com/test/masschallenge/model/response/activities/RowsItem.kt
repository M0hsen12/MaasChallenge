package com.test.masschallenge.model.response.activities

import com.google.gson.annotations.SerializedName

data class RowsItem(@SerializedName("priceEUR")
                    val priceEUR: PriceEUR,
                    @SerializedName("address")
                    val address: Address,
                    @SerializedName("siteUrl")
                    val siteUrl: String = "",
                    @SerializedName("meantFor")
                    val meantFor: List<String>?,
                    @SerializedName("media")
                    val media: List<MediaItem>?,
                    @SerializedName("descriptions")
                    val descriptions: Descriptions,
                    @SerializedName("durationType")
                    val durationType: String = "",
                    @SerializedName("tags")
                    val tags: List<String>?,
                    @SerializedName("duration")
                    val duration: String = "",
                    @SerializedName("availableMonths")
                    val availableMonths: List<String>?,
                    @SerializedName("storeUrl")
                    val storeUrl: String = "",
                    @SerializedName("company")
                    val company: Company,
                    @SerializedName("id")
                    val id: String = "",
                    @SerializedName("updated")
                    val updated: String = "")