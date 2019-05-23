package com.tinkoff.task.ui.list

data class ListState(
  val success: Boolean = false,  val loading: Boolean = false,
  val error: Throwable? = null
)

sealed class ItemState {
  data class ItemRechargePoint(
    val id: Int = Int.MIN_VALUE,
    val name: String = "",
    val fullName: String = "",
    val countryCode: String = "",
    val lat: Double = Double.MIN_VALUE,
    val lon: Double = Double.MIN_VALUE
  ) : ItemState()
}

sealed class ListStateIntent {
  object GetPartners : ListStateIntent()
}

sealed class ListStateChange {
  class Error(val error: Throwable) : ListStateChange()
  object HideError : ListStateChange()
  object Loading : ListStateChange()
  object Success : ListStateChange()
}