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
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jakewharton.rxbinding2.view.clicks
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tinkoff.task.common.BaseFragment
import com.tinkoff.task.common.BaseView
import com.tinkoff.task.databinding.FragmentMapBinding
import com.tinkoff.task.ui.map.MapStateIntent.GetDepositePointAround
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<FragmentMapBinding>(), BaseView<MapState> {

  private val vmMapScreen: MapViewModel by viewModel()
  private val eventPublisher: PublishSubject<MapStateIntent> by lazy { vmMapScreen.eventPublisher }
  private var dialog: AlertDialog? = null
  private val locationManager by lazy { context!!.getSystemService(LOCATION_SERVICE) as LocationManager }
  private val rxPermissions by lazy { RxPermissions(this) }
  private lateinit var googleMap: GoogleMap
  private lateinit var rxPermissionDisposable: Disposable
  override fun resLayoutId(): Int = com.tinkoff.task.R.layout.fragment_map

  override fun onCreate(savedInstanceState: Bundle?) =
    super.onCreate(savedInstanceState).also { handleStates() }

  @SuppressLint("MissingPermission")
  override fun onResume() {
    super.onResume()
    viewBinding!!.map.onResume()

  }

  override fun onPause() {
    viewBinding!!.map.onPause()
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

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = super.onCreateView(inflater, container, savedInstanceState)
    .also {
      initIntents()
      initMap(savedInstanceState)

      viewBinding!!.root.clicks()
        .subscribe { findNavController().navigate(com.tinkoff.task.R.id.action_rootFragment_to_detailFragment) }
    }

  @SuppressLint("MissingPermission")
  private fun initMap(savedInstanceState: Bundle?) {
    with(viewBinding!!) {
      map.onCreate(savedInstanceState)
      map.getMapAsync {
        googleMap = it
        googleMap.uiSettings.isZoomControlsEnabled = true

        rxPermissionDisposable = rxPermissions.requestEachCombined(
          Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.ACCESS_COARSE_LOCATION
        )
          .subscribe {
            val isGPSEnabled =
              locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
              )
            if (isGPSEnabled && it.granted) {
              googleMap.isMyLocationEnabled = true
            } else {

            }
          }

        googleMap.setOnCameraIdleListener {
          with(googleMap.projection.visibleRegion.latLngBounds) {
            val results = FloatArray(5)
            Location.distanceBetween(
              northeast.latitude,
              northeast.longitude,
              southwest.latitude,
              southwest.longitude,
              results
            )

            val currentCenterLongitude = googleMap.cameraPosition.target.longitude
            val currentCenterLatitude = googleMap.cameraPosition.target.latitude

            eventPublisher.onNext(
              GetDepositePointAround(
                currentCenterLongitude,
                currentCenterLatitude,
                results[0].toInt()
              )
            )
          }
        }
      }
    }
  }

  override fun initIntents() {
    viewSubscriptions = Observable.merge(
      listOf(
        Observable.just(true),
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
            MarkerOptions().position(LatLng(it.lat, it.lon)).title(it.partnerName)
          )
        }
      }
    }
  }

  private fun createLocationSettingsDialog(context: Context, message: String): AlertDialog.Builder =
    with(AlertDialog.Builder(getContext())) {
      setMessage(message)
      setPositiveButton(com.tinkoff.task.R.string.go_to_location_settings) { _, _ ->
        dialog?.dismiss()
        val msg = getString(com.tinkoff.task.R.string.location_service_disabled)
        val intent = if (message == msg) Intent(
          Settings.ACTION_LOCATION_SOURCE_SETTINGS
        ) else Intent(Settings.ACTION_SETTINGS)
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        ContextCompat.startActivity(context, intent, null)
      }
    }

}

