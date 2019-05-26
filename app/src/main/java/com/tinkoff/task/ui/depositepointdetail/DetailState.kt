package com.tinkoff.task.ui.depositepointdetail

import com.tinkoff.task.repository.domain.entity.DepositePoint
import com.tinkoff.task.repository.domain.entity.Partner

data class DetailState(
  val success: Boolean = false, val loading: Boolean = false,
  val error: Throwable? = null,
  val partnerName: String = "",
  val fullAddress: String = "",
  val picture: String = "",
  val workingHours: String = "",
  val enrollment: String = "",
  val restrictions: String = "",
  val conditions: String = "",
  val site: String = "",
  val phone: String = ""
)

sealed class DetailStateIntent {
  class GetDataForDepositePoint(val fullAddress: String) : DetailStateIntent()
}

sealed class DetailStateChange {
  class Error(val error: Throwable) : DetailStateChange()
  object HideError : DetailStateChange()
  object Loading : DetailStateChange()
  class DataReceived(val depositePoint: DepositePoint, val partner: Partner) :
    DetailStateChange()
}