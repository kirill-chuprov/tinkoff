package com.tinkoff.task.ui.depositepointslist

import com.tinkoff.task.common.BaseViewModel
import com.tinkoff.task.common.createPictureUrl
import com.tinkoff.task.common.startWithAndErrHandleWithIO
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.Partner
import com.tinkoff.task.repository.domain.interactors.ObserveDepositePointsUseCase
import com.tinkoff.task.repository.domain.interactors.ObservePartnersUseCase
import com.tinkoff.task.ui.depositepointslist.ItemState.ItemDepositePoint
import com.tinkoff.task.ui.depositepointslist.ListStateChange.DepositePointsChanged
import com.tinkoff.task.ui.depositepointslist.ListStateChange.Error
import com.tinkoff.task.ui.depositepointslist.ListStateChange.HideError
import com.tinkoff.task.ui.depositepointslist.ListStateChange.Loading
import com.tinkoff.task.ui.depositepointslist.ListStateChange.PartnersChanged
import com.tinkoff.task.ui.depositepointslist.ListStateIntent.ObserveDepositePoints
import com.tinkoff.task.ui.depositepointslist.ListStateIntent.ObservePartners
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ListViewModel(
  private val observePartnersUseCase: ObservePartnersUseCase,
  private val observeDepositePointsUseCase: ObserveDepositePointsUseCase,
  private val density: String
) :
  BaseViewModel<ListState>() {

  internal val eventPublisher: PublishSubject<ListStateIntent> by lazy { PublishSubject.create<ListStateIntent>() }

  override fun initState(): ListState = ListState()

  override fun viewIntents(intentStream: Observable<*>): Observable<Any> =
    Observable.merge(
      listOf(
        intentStream.ofType(ObservePartners::class.java)
          .switchMap {
            observePartnersUseCase.observePartners()
              .map { PartnersChanged(it) }
              .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) }
          }
        ,
        intentStream.ofType(ObserveDepositePoints::class.java)
          .switchMap {
            observeDepositePointsUseCase.observeDepositePoints()
              .map {
                DepositePointsChanged(it.map { it.toPresentation() })
              }
              .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) }
          }
      )
    )

  private fun DepositePoint.toPresentation() = ItemDepositePoint(
    externalId = externalId,
    partnerName = partnerName,
    lat = location.latitude,
    lon = location.longitude,
    workHours = workHours,
    addressInfo = addressInfo,
    fullAddress = fullAddress,
    verificationInfo = verificationInfo
  )

  override fun reduceState(previousState: ListState, stateChange: Any): ListState =
    when (stateChange) {
      is Loading -> previousState.copy(
        loading = true,
        success = false,
        error = null
      )

      is DepositePointsChanged -> previousState.copy(
        loading = false,
        depositePoints = addPicturesToPoints(stateChange.depositePoints, previousState.partners),
        success = true,
        error = null
      )

      is PartnersChanged -> previousState.copy(
        loading = false,
        partners = stateChange.partners,
        success = false,
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

  private fun addPicturesToPoints(
    points: List<ItemDepositePoint>,
    partners: List<Partner>
  ): List<ItemDepositePoint> {
    val pointsWithPicture = mutableListOf<ItemDepositePoint>()
    points.forEach { point ->
      partners.forEach { partner ->
        if (partner.id == point.partnerName) pointsWithPicture.add(
          point.copy(
            picture = createPictureUrl(
              density,
              partner.picture
            )
          )
        )
      }
    }
    return pointsWithPicture
  }
}