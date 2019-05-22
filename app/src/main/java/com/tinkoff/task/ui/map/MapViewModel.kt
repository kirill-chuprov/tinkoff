package com.tinkoff.task.ui.map

import androidx.lifecycle.Transformations.map
import com.tinkoff.task.common.BaseViewModel
import com.tinkoff.task.ui.map.MapStateChange.Error
import com.tinkoff.task.ui.map.MapStateChange.HideError
import com.tinkoff.task.ui.map.MapStateChange.Loading
import com.tinkoff.task.ui.map.MapStateChange.Success
import com.tinkoff.task.ui.map.MapStateIntent.GetObjectsInBoundaries
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MapViewModel(private val getObjectInBoundariesUseCase: GetObjectInBoundariesUseCase) :
  BaseViewModel<MapState>() {

  internal val eventPublisher: PublishSubject<MapStateIntent> by lazy { PublishSubject.create<MapStateIntent>() }

  override fun initState(): MapState = MapState()

  override fun viewIntents(intentStream: Observable<*>): Observable<Any> =
    Observable.merge(
      listOf(
        intentStream.ofType(GetObjectsInBoundaries::class.java)
          .switchMap {

            .map { Success }
            .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) }
          }

      )
    )

  override fun reduceState(previousState: MapState, stateChange: Any): MapState =
    when (stateChange) {
      is Loading -> previousState.copy(
        loading = true,
        success = false,
        error = null
      )

      is Success -> previousState.copy(
        loading = false,
        success = true,
        error = null
      )

      is Error -> previousState.copy(
        loading = false,
        success = false,
        error = stateChange.error
      )

      is HideError -> previousState.copy(error = null)

      else -> previousState
    }
}