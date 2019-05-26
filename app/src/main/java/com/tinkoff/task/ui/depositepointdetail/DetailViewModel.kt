package com.tinkoff.task.ui.depositepointdetail

import androidx.core.text.HtmlCompat
import com.tinkoff.task.common.BaseViewModel
import com.tinkoff.task.common.createPictureUrl
import com.tinkoff.task.common.errHandleWithIO
import com.tinkoff.task.common.startWithAndErrHandleWithIO
import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.Partner
import com.tinkoff.task.repository.domain.interactors.GetDepositePointUseCase
import com.tinkoff.task.repository.domain.interactors.GetPartnerForPointUseCase
import com.tinkoff.task.ui.depositepointdetail.DetailStateChange.DataReceived
import com.tinkoff.task.ui.depositepointdetail.DetailStateChange.Error
import com.tinkoff.task.ui.depositepointdetail.DetailStateChange.HideError
import com.tinkoff.task.ui.depositepointdetail.DetailStateChange.Loading
import com.tinkoff.task.ui.depositepointdetail.DetailStateIntent.GetDataForDepositePoint
import com.tinkoff.task.ui.depositepointslist.ListStateChange
import io.reactivex.Observable

class DetailViewModel(
  private val getDepositePointUseCase: GetDepositePointUseCase,
  private val getPartnerForPointUseCase: GetPartnerForPointUseCase,
  private val density: String

) : BaseViewModel<DetailState>() {

  override fun initState(): DetailState = DetailState()

  override fun viewIntents(intentStream: Observable<*>): Observable<Any> =
    Observable.merge(
      listOf(
        intentStream.ofType(GetDataForDepositePoint::class.java)
          .switchMap {
            getDepositePointUseCase.getDepositePointUseCase(it.fullAddress)
              .flatMap { depositePoint: DepositePoint ->
                getPartnerForPointUseCase.getPartnerById(depositePoint.partnerName)
                  .map { partner: Partner ->
                    DataReceived(depositePoint, partner)
                  }
              }
              .toObservable()
              .errHandleWithIO{ Observable.just(
                ListStateChange.Error(it),
                ListStateChange.HideError
              ) }
          }
      )
    )

  override fun reduceState(previousState: DetailState, stateChange: Any): DetailState =
    when (stateChange) {
      is Loading -> previousState.copy(
        loading = true,
        success = false,
        error = null
      )

      is DataReceived -> previousState.copy(
        loading = false,
        success = true,
        partnerName = stateChange.depositePoint.partnerName,
        fullAddress = stateChange.depositePoint.fullAddress,
        picture = createPictureUrl(density, stateChange.partner.picture),
        workingHours = HtmlCompat.fromHtml(
          stateChange.depositePoint.workHours,
          HtmlCompat.FROM_HTML_MODE_LEGACY
        ).toString(),
        enrollment = HtmlCompat.fromHtml(
          stateChange.partner.depositionDuration,
          HtmlCompat.FROM_HTML_MODE_LEGACY
        ).toString(),
        restrictions = HtmlCompat.fromHtml(
          stateChange.partner.limitations,
          HtmlCompat.FROM_HTML_MODE_LEGACY
        ).toString(),
        conditions = HtmlCompat.fromHtml(
          stateChange.partner.description,
          HtmlCompat.FROM_HTML_MODE_LEGACY
        ).toString(),
        site = stateChange.partner.url,
        phone = stateChange.depositePoint.phones,
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