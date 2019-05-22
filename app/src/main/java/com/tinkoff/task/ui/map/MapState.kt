package com.tinkoff.task.ui.map

data class MapState(
  val success: Boolean = false, val loading: Boolean = false,
  val error: Throwable? = null
)

sealed class MapStateIntent {
  object GetSampleData : MapStateIntent()
  class GetObjectsNearYou(
    val longitude: Double,
    val latitude: Double,
    val radius: Int
  ) : MapStateIntent()

  class GetObjectsInBoundaries(
    val southwestLongitude: Double,
    val northeastLatitude: Double,
    val northeastLongitude: Double,
    val southwestLatitude: Double,
    val radius: Float
  ) : MapStateIntent()

}

sealed class MapStateChange {
  class Error(val error: Throwable) : MapStateChange()
  object HideError : MapStateChange()
  object Loading : MapStateChange()
  object Success : MapStateChange()
}