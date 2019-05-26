package com.tinkoff.task.ui.map

import com.tinkoff.task.common.BaseViewModel
import com.tinkoff.task.common.errHandleWithIO
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.interactors.GetDepositePointAroundUseCase
import com.tinkoff.task.repository.domain.interactors.GetPartnersUseCase
import com.tinkoff.task.repository.domain.interactors.RunCleanPointsTaskUseCase
import com.tinkoff.task.ui.depositepointslist.ListStateChange
import com.tinkoff.task.ui.map.MapStateChange.DepositePointsReceivedAndSaved
import com.tinkoff.task.ui.map.MapStateChange.Error
import com.tinkoff.task.ui.map.MapStateChange.HideError
import com.tinkoff.task.ui.map.MapStateChange.Loading
import com.tinkoff.task.ui.map.MapStateChange.PartnersWereReceivedAndSaved
import com.tinkoff.task.ui.map.MapStateIntent.GetDepositePointAround
import com.tinkoff.task.ui.map.MapStateIntent.GetPartners
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MapViewModel(
  private val getDepositePointAroundUseCase: GetDepositePointAroundUseCase,
  private val getPartnersUseCase: GetPartnersUseCase,
  private val runCleanPointsTaskUseCase: RunCleanPointsTaskUseCase
) :
  BaseViewModel<MapState>() {

  internal val eventPublisher: PublishSubject<MapStateIntent> by lazy { PublishSubject.create<MapStateIntent>() }

  override fun initState(): MapState = MapState()

  override fun vmIntents(): Observable<Any> =
    runCleanPointsTaskUseCase.runCleanPointsTaskUseCase().toObservable()

  override fun viewIntents(intentStream: Observable<*>): Observable<Any> =
    Observable.merge(
      listOf(
        intentStream.ofType(GetDepositePointAround::class.java)
          .switchMap {
            getDepositePointAroundUseCase.getDepositePointAround(
              it.longitude,
              it.latitude,
              it.radius
            )
              .map { DepositePointsReceivedAndSaved(it.map { it.toPresentation() }) }
              .errHandleWithIO {
                Observable.just(
                  ListStateChange.Error(it),
                  ListStateChange.HideError
                )
              }
          },
        intentStream.ofType(GetPartners::class.java)
          .switchMap {
            getPartnersUseCase.getPartnersUseCase("Credit")
              .toSingleDefault(Notification.createOnComplete<Any>())
              .toObservable()
              .map { PartnersWereReceivedAndSaved }
              .errHandleWithIO {
                Observable.just(
                  ListStateChange.Error(it),
                  ListStateChange.HideError
                )
              }
          }

      )
    )

  private fun DepositePoint.toPresentation() = ItemState.ItemDepositePoint(
    externalId = externalId,
    partnerName = partnerName,
    lat = location.latitude,
    lon = location.longitude,
    workHours = workHours,
    addressInfo = addressInfo,
    fullAddress = fullAddress,
    verificationInfo = verificationInfo
  )

  override fun reduceState(previousState: MapState, stateChange: Any): MapState =
    when (stateChange) {
      is Loading -> previousState.copy(
        loading = true,
        success = false,
        error = null
      )

      is DepositePointsReceivedAndSaved -> previousState.copy(
        loading = false,
        depositePoints = stateChange.depositePoints,
        success = true,
        error = null
      )

      is Error -> previousState.copy(
        loading = false,
        success = false,
        error = stateChange.error
      )

      is PartnersWereReceivedAndSaved -> previousState.copy(
        loading = false,
        success = true,
        werePartnersSavedInDB = true,
        error = null
      )

      is HideError -> previousState.copy(error = null)

      else -> previousState
    }
}