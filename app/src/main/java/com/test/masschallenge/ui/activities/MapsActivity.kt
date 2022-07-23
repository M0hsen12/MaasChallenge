package com.test.masschallenge.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.test.masschallenge.R
import com.test.masschallenge.databinding.ActivityMapsBinding
import com.test.masschallenge.di.modules.BindModule
import com.test.masschallenge.di.viewModelInjections.InjectionViewModelProvider
import com.test.masschallenge.ui.base.BaseActivity
import com.test.masschallenge.viewModel.activities.MapsActivityViewModel
import javax.inject.Inject
@BindModule
class MapsActivity  : BaseActivity<ActivityMapsBinding, MapsActivityViewModel>(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    @Inject
    lateinit var mViewModelFactoryActivity: InjectionViewModelProvider<MapsActivityViewModel>
    override fun getLayoutId() = R.layout.activity_maps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = mViewModelFactoryActivity.get(this, MapsActivityViewModel::class)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        // Add a marker in Sydney and move the camera
//        https://open-api.myhelsinki.fi/v1/places/?limit=1
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}