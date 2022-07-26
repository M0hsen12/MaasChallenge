package com.test.masschallenge.model.response.places

import com.google.gson.annotations.SerializedName

data class Images(@SerializedName("license_type")
                  val licenseType: LicenseType,
                  @SerializedName("copyright_holder")
                  val copyrightHolder: String = "",
                  @SerializedName("media_id")
                  val mediaId: String = "",
                  @SerializedName("url")
                  val url: String = "")