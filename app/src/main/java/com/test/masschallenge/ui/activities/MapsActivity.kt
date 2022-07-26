package com.test.masschallenge.ui.activities

import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.test.masschallenge.R
import com.test.masschallenge.databinding.ActivityMapsBinding
import com.test.masschallenge.di.modules.BindModule
import com.test.masschallenge.di.viewModelInjections.InjectionViewModelProvider
import com.test.masschallenge.model.response.places.DataItem
import com.test.masschallenge.ui.base.BaseActivity
import com.test.masschallenge.ui.bottomSheet.FragmentBottomSheetDetail
import com.test.masschallenge.viewModel.activities.MapsActivityViewModel
import java.util.ArrayList
import javax.inject.Inject

@BindModule
class MapsActivity : BaseActivity<ActivityMapsBinding, MapsActivityViewModel>(),
    OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var mViewModelFactoryActivity: InjectionViewModelProvider<MapsActivityViewModel>
    override fun getLayoutId() = R.layout.activity_maps
    val listPlaces = ArrayList<LatLng>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = mViewModelFactoryActivity.get(this, MapsActivityViewModel::class)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        observeData()
    }

    private fun observeData() {
        viewModel?.placesLiveData?.observe(this) {
            it.data?.forEach { dataItem ->
                listPlaces.add(LatLng(dataItem.location.lat, dataItem.location.lon))
            }
            setupMarkers(it.data)
        }

    }

    private fun setupMarkers(data: List<DataItem>?) {
        data?.forEach {
            val a = mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(it.location.lat, it.location.lon))
                    .title(it.name.en)
                    .snippet(getString(R.string.clickForInfo))
                    .icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                    )
            )
            a?.tag = it.id

        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val helsinki = LatLng(60.188, 24.950)

        mMap.addMarker(MarkerOptions().position(helsinki).title("Marker in helsinki"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(helsinki, 17F))
        mMap.setOnMarkerClickListener(this)

        viewModel?.getPlaces(helsinki.latitude, helsinki.longitude)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        Log.e("TAG", "onMarker222Click: ${marker.tag}")
        val detail = FragmentBottomSheetDetail.newInstance(marker.tag.toString())
        detail.show(supportFragmentManager, BOTTOM_SHEET_DETAIL_TAG)
        return false
    }


    companion object {
        const val BOTTOM_SHEET_DETAIL_TAG = "BottomSheetDetail"

    }
}