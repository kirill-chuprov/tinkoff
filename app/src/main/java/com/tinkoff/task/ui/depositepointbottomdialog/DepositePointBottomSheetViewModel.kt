package com.tinkoff.task.ui.depositepointbottomdialog

import com.tinkoff.task.common.BaseViewModel
import com.tinkoff.task.common.createPictureUrl
import com.tinkoff.task.common.startWithAndErrHandleWithIO
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.Partner
import com.tinkoff.task.repository.domain.interactors.GetDepositePointUseCase
import com.tinkoff.task.repository.domain.interactors.GetPartnerForPointUseCase
import com.tinkoff.task.ui.depositepointbottomdialog.DepositePointBottomSheetStateChange.Error
import com.tinkoff.task.ui.depositepointbottomdialog.DepositePointBottomSheetStateChange.HideError
import com.tinkoff.task.ui.depositepointbottomdialog.DepositePointBottomSheetStateChange.Loading
import com.tinkoff.task.ui.depositepointbottomdialog.DepositePointBottomSheetStateChange.PointReceived
import com.tinkoff.task.ui.depositepointbottomdialog.DepositePointBottomSheetStateIntent.GetDataForDepositePoint
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class DepositePointBottomSheetViewModel(
  private val getDepositePointUseCase: GetDepositePointUseCase,
  private val getPartnerForPointUseCase: GetPartnerForPointUseCase,
  private val density: String
) :
  BaseViewModel<DepositePointBottomSheetState>() {

  internal val eventPublisher: PublishSubject<DepositePointBottomSheetStateIntent> by lazy { PublishSubject.create<DepositePointBottomSheetStateIntent>() }

  override fun initState(): DepositePointBottomSheetState = DepositePointBottomSheetState()

  override fun viewIntents(intentStream: Observable<*>): Observable<Any> =
    Observable.merge(
      listOf(
        intentStream.ofType(GetDataForDepositePoint::class.java)
          .switchMap {
            getDepositePointUseCase.getDepositePointUseCase(it.fullAddress)
              .flatMap { depositePoint: DepositePoint ->
                getPartnerForPointUseCase.getPartnerById(depositePoint.partnerName)
                  .map { partner: Partner ->
                    PointReceived(
                      depositePoint.copy(
                        picture = createPictureUrl(
                          density,
                          partner.picture
                        )
                      )
                    )
                  }
              }
              .toObservable()
              .startWithAndErrHandleWithIO(Loading) { Observable.just(Error(it), HideError) }
          }
      )
    )

  override fun reduceState(
    previousState: DepositePointBottomSheetState,
    stateChange: Any
  ): DepositePointBottomSheetState =
    when (stateChange) {
      is Loading -> previousState.copy(
        loading = true,
        success = false,
        error = null
      )

      is PointReceived -> previousState.copy(
        loading = false,
        success = true,
        depositePoint = stateChange.depositePoint,
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