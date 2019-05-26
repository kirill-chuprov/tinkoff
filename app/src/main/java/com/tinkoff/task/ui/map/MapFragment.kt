package com.tinkoff.task.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tinkoff.task.R
import com.tinkoff.task.R.layout
import com.tinkoff.task.R.string
import com.tinkoff.task.common.BaseFragment
import com.tinkoff.task.common.BaseView
import com.tinkoff.task.databinding.FragmentMapBinding
import com.tinkoff.task.ui.depositepointbottomdialog.DepositePointBottomSheetFragment
import com.tinkoff.task.ui.map.MapStateIntent.GetDepositePointAround
import com.tinkoff.task.ui.map.MapStateIntent.GetPartners
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<FragmentMapBinding>(), BaseView<MapState> {

  private lateinit var locationCallback: LocationCallback
  private lateinit var locationRequest: LocationRequest

  private val radius10Km = 10_000

  private val fusedLocationClient: FusedLocationProviderClient by lazy {
    LocationServices.getFusedLocationProviderClient(context!!)
  }

  private val vmMapScreen: MapViewModel by viewModel()
  private val eventPublisher: PublishSubject<MapStateIntent> by lazy { vmMapScreen.eventPublisher }

  private val locationManager by lazy { context!!.getSystemService(LOCATION_SERVICE) as LocationManager }
  private val rxPermissions by lazy { RxPermissions(this) }

  private lateinit var googleMap: GoogleMap
  private lateinit var rxPermissionDisposable: Disposable

  override fun resLayoutId(): Int = layout.fragment_map

  override fun onCreate(savedInstanceState: Bundle?) =
    super.onCreate(savedInstanceState).also {
      handleStates()
      configureLocationTracking()
    }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = super.onCreateView(inflater, container, savedInstanceState)
    .also {
      initIntents()
      initMap(savedInstanceState)
    }

  @SuppressLint("MissingPermission")
  override fun onResume() {
    super.onResume()
    viewBinding!!.map.onResume()
  }

  override fun onPause() {
    viewBinding!!.map.onPause()
    stopLocationUpdates()
    super.onPause()
  }

  override fun onStop() {
    super.onStop()
    viewBinding!!.map.onStop()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    viewBinding!!.map.onLowMemory()
  }

  @SuppressLint("MissingPermission")
  private fun initMap(savedInstanceState: Bundle?) {
    with(viewBinding!!) {
      map.onCreate(savedInstanceState)
      map.getMapAsync {

        //basic init
        googleMap = it
        googleMap.uiSettings.isZoomControlsEnabled = true

        //request permissions
        rxPermissionDisposable = rxPermissions.requestEachCombined(
          Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.ACCESS_COARSE_LOCATION
        )
          .subscribe {
            if (isGPSEnabled() && it.granted) {
              googleMap.isMyLocationEnabled = true
              fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            }
          }


        //setup camera changed listener
        googleMap.setOnCameraIdleListener {
          with(googleMap.projection.visibleRegion.latLngBounds) {
            val radius = computeRadius(this)

            val currentCenterLongitude = googleMap.cameraPosition.target.longitude
            val currentCenterLatitude = googleMap.cameraPosition.target.latitude

            if (radius < radius10Km)
              eventPublisher.onNext(
                GetDepositePointAround(
                  currentCenterLongitude,
                  currentCenterLatitude,
                  radius
                )
              )
          }
        }


        //setup marker click
        googleMap.setOnMarkerClickListener {
          DepositePointBottomSheetFragment.newInstance(it.title)
            .show(childFragmentManager, "DepositePointBottomSheetFragment")
          true
        }
      }
    }
  }

  private fun computeRadius(latLngBounds: LatLngBounds): Int {
    val results = FloatArray(5)
    with(latLngBounds) {
      Location.distanceBetween(
        northeast.latitude,
        northeast.longitude,
        southwest.latitude,
        southwest.longitude,
        results
      )
    }
    return results[0].toInt()
  }

  private fun configureLocationTracking() {
    // Create location callback to receive coordinates
    locationCallback = object : LocationCallback() {
      override fun onLocationResult(locationResult: LocationResult?) {
        locationResult ?: return
        if (::googleMap.isInitialized) {
          val lat = locationResult.lastLocation.latitude
          val lan = locationResult.lastLocation.longitude
          googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lan), 13f))
        }
      }
    }

    // Configure location request interval and priority - 10 min
    locationRequest = LocationRequest.create()
    with(locationRequest) {
      priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
      interval = 600000
      fastestInterval = 600000
    }
  }

  private fun isGPSEnabled(): Boolean =
    locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
      LocationManager.NETWORK_PROVIDER
    )

  private fun stopLocationUpdates() {
    fusedLocationClient.removeLocationUpdates(locationCallback)
  }

  override fun initIntents() {
    viewSubscriptions = Observable.merge(
      listOf(
        Observable.just(GetPartners),
        eventPublisher
      )
    ).subscribe(vmMapScreen.viewIntentsConsumer())
  }

  override fun handleStates() {
    vmMapScreen.stateReceived().observe(this, Observer { state -> render(state) })
  }

  override fun render(state: MapState) {
    viewBinding!!.viewState = state
    if (state.success) {
      with(state.depositePoints) {
        googleMap.clear()
        forEach {
          googleMap.addMarker(
            MarkerOptions().position(
              LatLng(
                it.lat,
                it.lon
              )
            ).title(it.fullAddress)
          )
        }
      }
    }
  }
}

