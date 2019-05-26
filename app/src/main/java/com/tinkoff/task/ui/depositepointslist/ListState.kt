package com.tinkoff.task.ui.depositepointslist

import com.tinkoff.task.repository.domain.entity.Partner
import com.tinkoff.task.ui.depositepointslist.ItemState.ItemDepositePoint

const val FULL_ADDRESS: String = "FULL_ADDRESS"
const val PICTURE: String = "PICTURE"
const val PARTNER_NAME: String = "PARTNER_NAME"

data class ListState(
  val success: Boolean = false, val loading: Boolean = false,
  val error: Throwable? = null,
  val partners: List<Partner> = emptyList(),
  val depositePoints: List<ItemDepositePoint> = emptyList()
)

sealed class ItemState {
  data class ItemDepositePoint(
    val externalId: Int = Int.MIN_VALUE,
    val partnerName: String = "",
    val lat: Double = Double.MIN_VALUE,
    val lon: Double = Double.MIN_VALUE,
    val picture: String = "",
    val workHours: String = "",
    val addressInfo: String = "",
    val fullAddress: String = "",
    val verificationInfo: String = ""
  ) : ItemState()
}

sealed class ListStateIntent {
  object ObservePartners : ListStateIntent()
  class GoToPointDetail(
    val fullAddress: String,
    val picture: String,
    val partnerName: String
  ) :
    ListStateIntent()

  object ObserveDepositePoints : ListStateIntent()
}

sealed class ListStateChange {
  class Error(val error: Throwable) : ListStateChange()
  object HideError : ListStateChange()
  object Loading : ListStateChange()
  class DepositePointsChanged(val depositePoints: List<ItemDepositePoint>) :
    ListStateChange()

  class PartnersChanged(val partners: List<Partner>) :
    ListStateChange()
}