package com.tinkoff.task.ui.map

data class MapState(
  val success: Boolean = false,  val loading: Boolean = false,
  val error: Throwable? = null
)

sealed class MapStateIntent {
  object GetSampleData : MapStateIntent()
}

sealed class MapStateChange {
  class Error(val error: Throwable) : MapStateChange()
  object HideError : MapStateChange()
  object Loading : MapStateChange()
  object Success : MapStateChange()
}