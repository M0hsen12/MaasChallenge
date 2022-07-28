package com.test.masschallenge.di

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.masschallenge.di.data.network.NetworkManager
import com.test.masschallenge.model.MapMarkerEntity
import com.test.masschallenge.model.response.activities.Activities
import com.test.masschallenge.model.response.activities.RowsItem
import com.test.masschallenge.model.response.events.Events
import com.test.masschallenge.model.response.places.DataItem
import com.test.masschallenge.model.response.places.Places
import com.test.masschallenge.ui.activities.MapsActivity


class DependencyContainer {

    lateinit var getPlaces: Places
    lateinit var getEmptyPlace: Places
    lateinit var getEvents: Events
    lateinit var getActivities: Activities
    lateinit var dataManger: DataMangerTest

    fun build() {
        this.javaClass.classLoader?.let { classLoader ->
            dataManger = DataMangerTest(classLoader)
            dataManger.build()
            getPlaces = dataManger.produceListOfPlaces()
            getEmptyPlace = dataManger.produceEmptyListOfPlaces()
            getEvents = dataManger.produceListOfEvents()
            getActivities = dataManger.produceListOfActivities()
        }

    }

    fun eventsListToMapMarker(list: List<com.test.masschallenge.model.response.events.DataItem>):
            List<MapMarkerEntity> {

        val tempList = arrayListOf<MapMarkerEntity>()

        list.forEach {
            tempList.add(
                MapMarkerEntity(
                    id = it.id,
                    title = it.name.fi,
                    type = MapsActivity.EVENTS_KEY,
                    lat = it.location.lat,
                    lng = it.location.lon
                )
            )

        }
        return tempList

    }

    fun activitiesListToMapMarker(list: List<RowsItem>):
            List<MapMarkerEntity> {

        val tempList = arrayListOf<MapMarkerEntity>()

        list.forEach {
            tempList.add(
                MapMarkerEntity(
                    id = it.id,
                    title = it.descriptions.en.name,
                    type = MapsActivity.ACTIVITIES_KEY,
                    lat = it.address.location.lon,
                    lng = it.address.location.lat
                )
            )

        }
        return tempList
    }

    fun placesListToMapMarker(list: List<DataItem>):
            List<MapMarkerEntity> {

        val tempList = arrayListOf<MapMarkerEntity>()

        list.forEach {
            tempList.add(
                MapMarkerEntity(
                    id = it.id,
                    title = it.name.en,
                    type = MapsActivity.PLACES_KEY,
                    lat = it.location.lat,
                    lng = it.location.lon
                )
            )

        }
        return tempList
    }


}