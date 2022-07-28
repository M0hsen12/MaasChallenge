package com.test.masschallenge.model.request

import com.test.masschallenge.model.response.places.Images

data class UniversalDetail(
    val id:String,
    val title:String,
    val intro:String,
    val description:String,
    val city:String,
    val neighbor:String,
    val street:String,
    val postalCode:String,
    val link:String,
    val images:List<Images>,
)