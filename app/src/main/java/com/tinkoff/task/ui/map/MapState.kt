package com.tinkoff.task.ui.map

import com.tinkoff.task.ui.map.ItemState.ItemDepositePoint

data class MapState(
  val success: Boolean = false,
  val loading: Boolean = false,
  val werePartnersSavedInDB: Boolean = false,
  val depositePoints: List<ItemState.ItemDepositePoint> = emptyList(),
  val error: Throwable? = null
)

sealed class MapStateIntent {
  object GetPartners : MapStateIntent()
  class GetDepositePointAround(
    val longitude: Double,
    val latitude: Double,
    val radius: Int
  ) : MapStateIntent()
}

sealed class ItemState {
  data class ItemDepositePoint(
    val externalId: Int = Int.MIN_VALUE,
    val partnerName: String = "",
    val lat: Double = Double.MIN_VALUE,
    val lon: Double = Double.MIN_VALUE,
    val workHours: String = "",
    val addressInfo: String = "",
    val fullAddress: String = "",
    val verificationInfo: String = ""
  ) : ItemState()
}

sealed class MapStateChange {
  class Error(val error: Throwable) : MapStateChange()
  object HideError : MapStateChange()
  object Loading : MapStateChange()
  object PartnersWereReceivedAndSaved : MapStateChange()
  class DepositePointsReceivedAndSaved(val depositePoints: List<ItemDepositePoint>) :
    MapStateChange()
}