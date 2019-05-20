package com.tinkoff.task.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.jakewharton.rxbinding2.view.clicks
import com.tinkoff.task.R
import com.tinkoff.task.common.BaseFragment
import com.tinkoff.task.common.BaseView
import com.tinkoff.task.databinding.FragmentMapBinding
import com.tinkoff.task.ui.map.MapStateIntent.GetSampleData
import io.reactivex.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<FragmentMapBinding>(), BaseView<MapState> {

  private val vmMapScreen: MapViewModel by viewModel()
  private lateinit var googleMap: GoogleMap

  override fun resLayoutId(): Int = R.layout.fragment_map

  override fun onCreate(savedInstanceState: Bundle?) =
    super.onCreate(savedInstanceState).also { handleStates() }

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
        .subscribe { findNavController().navigate(R.id.action_rootFragment_to_detailFragment) }
    }

  private fun initMap(savedInstanceState: Bundle?) {
    with(viewBinding!!) {
      map.onCreate(savedInstanceState)
      map.getMapAsync {
        googleMap = it
        googleMap.ena

      }
    }
  }

  override fun initIntents() {
    viewSubscriptions = Observable.merge(
      listOf(
        Observable.just(GetSampleData)
      )
    ).subscribe(vmMapScreen.viewIntentsConsumer())
  }

  override fun handleStates() {
    vmMapScreen.stateReceived().observe(this, Observer { state -> render(state) })
  }

  override fun render(state: MapState) {
    viewBinding!!.viewState = state
  }
}