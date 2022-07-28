package com.test.masschallenge.viewModel.activities

import com.test.masschallenge.di.DependencyContainer
import com.test.masschallenge.model.MapMarkerEntity
import com.test.masschallenge.model.response.activities.Activities
import com.test.masschallenge.model.response.events.Events
import com.test.masschallenge.model.response.places.Places
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/*
1.getPlaces_success_ConfirmData
2.getPlaces_success_mapListToMapMarker
3.getPlaces_failure_emptyListForPlaces
4.getPlaces_failure_emptyListForPlaces_NoEffectOnMarkerList


 */
class ActivityMainViewModelTest {
    //    system in Test
//    MapsActivityViewModel

    //dependencies
    lateinit var dependencyContainer: DependencyContainer
    private var listPlaces: Places
    private var listEmptyPlaces: Places
    private var listEvents: Events
    private var listActivities: Activities

    init {
        dependencyContainer = DependencyContainer()
        dependencyContainer.build()
        listPlaces = dependencyContainer.getPlaces
        listEmptyPlaces = dependencyContainer.getEmptyPlace
        listEvents = dependencyContainer.getEvents
        listActivities = dependencyContainer.getActivities
    }

    @Test
    fun getPlaces_success_ConfirmData() {
        val placesList = this.listPlaces
        val listEvents = this.listEvents
        val listActivities = this.listActivities
        assertTrue { placesList.data.orEmpty().isNotEmpty() }
        assertTrue { listEvents.data.orEmpty().isNotEmpty() }
        assertTrue { listActivities.rows.orEmpty().isNotEmpty() }

    }

    @Test
    fun getPlaces_success_mapListToMapMarker() {

        val placesList = this.listPlaces
        val listEvents = this.listEvents
        val listActivities = this.listActivities
        val sizeOfListsBeforeZip =
            placesList.data.orEmpty().size + listEvents.data.orEmpty().size + listActivities.rows.orEmpty().size

        assertTrue { placesList.data.orEmpty().isNotEmpty() }
        assertTrue { listEvents.data.orEmpty().isNotEmpty() }
        assertTrue { listActivities.rows.orEmpty().isNotEmpty() }

        val markerList = ArrayList<MapMarkerEntity>()
        markerList.addAll(dependencyContainer.activitiesListToMapMarker(listActivities.rows.orEmpty()))
        markerList.addAll(dependencyContainer.eventsListToMapMarker(listEvents.data.orEmpty()))
        markerList.addAll( dependencyContainer.placesListToMapMarker(listPlaces.data.orEmpty()))

        assertTrue{markerList.size == sizeOfListsBeforeZip}
    }

    @Test
    fun getPlaces_failure_emptyListForPlaces() {
        val placesList = this.listEmptyPlaces
        val listEvents = this.listEvents
        val listActivities = this.listActivities

        assertTrue { listEvents.data.orEmpty().isNotEmpty() }
        assertTrue { listActivities.rows.orEmpty().isNotEmpty() }
        assertTrue { placesList.data.orEmpty().isEmpty() }
    }

    @Test
    fun getPlaces_failure_emptyListForPlaces_NoEffectOnMarkerList() {
        val placesList = this.listEmptyPlaces
        val listEvents = this.listEvents
        val listActivities = this.listActivities
        val sizeOfListsBeforeZip =
            placesList.data.orEmpty().size + listEvents.data.orEmpty().size + listActivities.rows.orEmpty().size

        assertTrue { placesList.data.orEmpty().isEmpty() }
        assertTrue { listEvents.data.orEmpty().isNotEmpty() }
        assertTrue { listActivities.rows.orEmpty().isNotEmpty() }

        val markerList = ArrayList<MapMarkerEntity>()
        markerList.addAll(dependencyContainer.activitiesListToMapMarker(listActivities.rows.orEmpty()))
        markerList.addAll(dependencyContainer.eventsListToMapMarker(listEvents.data.orEmpty()))
        markerList.addAll( dependencyContainer.placesListToMapMarker(placesList.data.orEmpty()))

        assertTrue{markerList.size == sizeOfListsBeforeZip}
    }


}