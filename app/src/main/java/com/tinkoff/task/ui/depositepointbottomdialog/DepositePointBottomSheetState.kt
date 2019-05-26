package com.tinkoff.task.ui.depositepointbottomdialog

import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.Partner

data class DepositePointBottomSheetState(
  val success: Boolean = false, val loading: Boolean = false,
  val error: Throwable? = null,
  val depositePoint: DepositePoint = DepositePoint.empty
)

sealed class DepositePointBottomSheetStateIntent {
  class GetDataForDepositePoint(val fullAddress: String) : DepositePointBottomSheetStateIntent()
}

sealed class DepositePointBottomSheetStateChange {
  class Error(val error: Throwable) : DepositePointBottomSheetStateChange()
  object HideError : DepositePointBottomSheetStateChange()
  object Loading : DepositePointBottomSheetStateChange()
  class PointReceived(val depositePoint: DepositePoint) :
    DepositePointBottomSheetStateChange()
}