package com.test.masschallenge.ui.activities

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
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
import com.test.masschallenge.util.isLocationIsOn
import com.test.masschallenge.util.materialSimpleMapProgressDialog
import com.test.masschallenge.viewModel.activities.MapsActivityViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@BindModule
class MapsActivity : BaseActivity<ActivityMapsBinding, MapsActivityViewModel>(),
    OnMapReadyCallback, GoogleMap.OnMarkerClickListener, LocationListener {

    private lateinit var mMap: GoogleMap
    private var isOnCreate = false

    @Inject
    lateinit var mViewModelFactoryActivity: InjectionViewModelProvider<MapsActivityViewModel>
    override fun getLayoutId() = R.layout.activity_maps
    private val listPlaces = ArrayList<LatLng>()
    lateinit var progressDialog: Dialog
    private val _mapLocationInput = BehaviorSubject.create<LatLng>()
    private val mapLocationInput = _mapLocationInput.toFlowable(BackpressureStrategy.LATEST)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = mViewModelFactoryActivity.get(this, MapsActivityViewModel::class)
        isOnCreate = true
        progressDialog = materialSimpleMapProgressDialog(this)
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnCameraIdleListener {
            _mapLocationInput.onNext(mMap.cameraPosition.target)
        }

        isLocationServicesThere()

    }

    private fun isLocationServicesThere() {
        if (isLocationIsOn(this)) {
            showNoLocationDialog()
        } else {
            tryGetUserLocation()
        }
    }

    private fun showNoLocationDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(getString(R.string.askForLocation))
        dialog.setPositiveButton(
            getString(R.string.setting)
        ) { dia, _ ->
            dia.dismiss()
            val myIntent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(myIntent)

        }

        dialog.setNegativeButton(
            getString(R.string.nope)
        ) { dia, _ ->
            dia.dismiss()
            Toast.makeText(this, getString(R.string.permissionDenied), Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

    private fun tryGetUserLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        progressDialog.show()
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_CODE
            )
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
            mMap.isMyLocationEnabled = true
            initBouncer()
        }

    }

    private fun initBouncer() {
        disposable.add(
            mapLocationInput
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.e("CCC", "getLocation: ${it.latitude} -- ${it.longitude}")
                    viewModel?.getPlaces(it.latitude, it.longitude)
                }
        )

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.e("TAG", "onRequestPermissionsResult: $requestCode")
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                tryGetUserLocation()
            } else
                Toast.makeText(
                    this,
                    getString(R.string.permissionDenied),
                    Toast.LENGTH_SHORT
                ).show()

        }
    }

    override fun onResume() {
        super.onResume()
        if (isOnCreate)
            isOnCreate = false
        else
            isLocationServicesThere()

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val detail = FragmentBottomSheetDetail.newInstance(marker.tag.toString())
        detail.show(supportFragmentManager, BOTTOM_SHEET_DETAIL_TAG)
        return false
    }


    override fun onLocationChanged(p0: Location) {
        val lat = 60.188
        val lng = 24.950
        val helsinki = LatLng(60.188, 24.950)

        mMap.addMarker(MarkerOptions().position(helsinki).title("Marker in helsinki"))
        progressDialog.takeIf { it.isShowing }?.dismiss()
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 17F))
        mMap.setOnMarkerClickListener(this)

        viewModel?.getPlaces(lat, lng)
        Log.e("TAG", "onLocationChanged: $p0.")
        Log.e("TAG", "onLocationChanged: ${p0.latitude}")
        Log.e("TAG", "onLocationChanged: ${p0.longitude}")
    }


    companion object {
        const val BOTTOM_SHEET_DETAIL_TAG = "BottomSheetDetail"
        const val PERMISSION_CODE = 101

    }
}