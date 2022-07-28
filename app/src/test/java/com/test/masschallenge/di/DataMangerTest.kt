package com.test.masschallenge.di

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.masschallenge.model.response.activities.Activities
import com.test.masschallenge.model.response.events.Events
import com.test.masschallenge.model.response.places.Meta
import com.test.masschallenge.model.response.places.Places
import com.test.masschallenge.model.response.places.Tags

class DataMangerTest(val testClassLoader:ClassLoader) {

    lateinit var apiCall:Places

    fun build (){
        apiCall = produceListOfPlaces()
    }
    fun produceListOfPlaces(): Places {
        return Gson()
            .fromJson(
                getPlacesFromFile("Places_list.json"),
                object : TypeToken<Places>() {}.type
            )
    }
    fun produceEmptyListOfPlaces(): Places {
        return Places(emptyList(), Meta(),Tags())
    }
    fun produceListOfEvents(): Events {
        return Gson()
            .fromJson(
                getPlacesFromFile("Events_list.json"),
                object : TypeToken<Events>() {}.type
            )
    }
    fun produceListOfActivities(): Activities {
        return Gson()
            .fromJson(
                getPlacesFromFile("Activities_list.json"),
                object : TypeToken<Activities>() {}.type
            )
    }

    fun getPlacesFromFile(fileName: String): String {
        return testClassLoader.getResource(fileName).readText()
    }
}