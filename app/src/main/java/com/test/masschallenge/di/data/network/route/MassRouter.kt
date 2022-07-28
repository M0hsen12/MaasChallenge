package com.test.masschallenge.di.data.network.route

import com.test.masschallenge.model.response.activities.Activities
import com.test.masschallenge.model.response.detail.Details
import com.test.masschallenge.model.response.events.Events
import com.test.masschallenge.model.response.places.Places
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MassRouter {

    @GET("/v2/places/")
    fun getPlaces(
        @Query("tags_search") tagSearch: String?,
        @Query("tags_filter") tagFilter: String?,
        @Query("distance_filter") distance: String?, // Syntax: 'lat,lon,range’
        @Query("language_filter") language: String?,
        @Query("limit") limit: String?,
        @Query("start") start: String?,
    ): Single<Places>


    @GET("/v2/place/{id}")
    fun getPlaceDetail(
        @Path("id") id: String?,
    ): Single<Response<Details>>

    @GET("/v2/activities")
    fun getActivities(
        @Query("tags_search") tagSearch: String?,
        @Query("tags_filter") tagFilter: String?,
        @Query("distance_filter") distance: String?, // Syntax: 'lat,lon,range’
        @Query("language_filter") language: String?,
        @Query("limit") limit: String?,
        @Query("start") start: String?,
    ): Single<Activities>


    @GET("/v1/events/")
    fun getEvents(
        @Query("tags_search") tagSearch: String?,
        @Query("tags_filter") tagFilter: String?,
        @Query("distance_filter") distance: String?, // Syntax: 'lat,lon,range’
        @Query("language_filter") language: String?,
        @Query("limit") limit: String?,
        @Query("start") start: String?,
    ): Single<Events>
}
